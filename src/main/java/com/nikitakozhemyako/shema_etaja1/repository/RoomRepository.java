package com.nikitakozhemyako.shema_etaja1.repository;

import com.nikitakozhemyako.shema_etaja1.domain.Room;
import org.springframework.stereotype.Repository;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomRepository {
    public List<Room> findAll() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Room> rooms = new ArrayList<>();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema s = sf.newSchema(new File("src/main/resources/data.xsd"));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setValidating(false);
        factory.setSchema(s);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new SimpleErrorHandler());

        Document document = builder.parse(new File("src/main/resources/data.xml"));
        NodeList roomsElement = document.getDocumentElement().getElementsByTagName("room");
        // Перебор всех элементов employee
        for (int i = 0; i < roomsElement.getLength(); i++) {
            Node employee = roomsElement.item(i);
            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = employee.getAttributes();
            // Добавление сотрудника. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            rooms.add(new Room(
                    Integer.parseInt(attributes.getNamedItem("numberFloor").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("numberRoom").getNodeValue()),
                    attributes.getNamedItem("predestination").getNodeValue(),
                    Double.parseDouble(attributes.getNamedItem("square").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("countDoor").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("countWindow").getNodeValue()),
                    Boolean.parseBoolean(attributes.getNamedItem("conditioner").getNodeValue())

            ));
        }
        return rooms;
    }

    public Room findById(int id) throws ClassNotFoundException, ParserConfigurationException, IOException, SAXException {
        final List<Room> roomList = findAll();
        for (Room room : roomList
        ) {
            if (id == room.getNumberRoom()) return room;
        }

        throw new ClassNotFoundException();
    }

    public void save(Room room) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        List<Room> rooms = findAll();
        rooms.add(room);
        roomWriteAll(rooms);
    }

    public void deleteById(int id) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        final List<Room> weapons = findAll().stream()
                .filter(w -> w.getNumberRoom() != id)
                .collect(Collectors.toList());
        roomWriteAll(weapons);
    }

    public void update(Room room, int id) throws ParserConfigurationException, IOException, SAXException {
        List<Room> rooms = findAll();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getNumberRoom() == id) {
                rooms.set(i,room);
            }
        }
    }





    public void roomWriteAll(List<Room> list) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("rooms");
        doc.appendChild(rootElement);

        for (Room r: list
             ) {
            Element room = doc.createElement("room");
            // add staff to root
            rootElement.appendChild(room);
            room.setAttribute("numberFloor", String.valueOf(r.getNumberFloor()));
            room.setAttribute("numberRoom", String.valueOf(r.getNumberRoom()));
            room.setAttribute("predestination", String.valueOf(r.getPredestination()));
            room.setAttribute("square", String.valueOf(r.getSquare()));
            room.setAttribute("countDoor", String.valueOf(r.getCountDoor()));
            room.setAttribute("countWindow", String.valueOf(r.getCountWindow()));
            room.setAttribute("conditioner", String.valueOf(r.isConditioner()));
        }
        // add xml attribute


        //...create XML elements, and others...

        // write dom document to a file
        try (FileOutputStream output =
                     new FileOutputStream("src/main/resources/data.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
