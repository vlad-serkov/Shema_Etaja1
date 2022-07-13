package com.nikitakozhemyako.shema_etaja1.service;

import com.nikitakozhemyako.shema_etaja1.domain.Room;
import com.nikitakozhemyako.shema_etaja1.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public void creat(Room weapon) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        roomRepository.save(weapon);
    }

    public List<Room> readAll() throws ParserConfigurationException, IOException, SAXException {
        return roomRepository.findAll();
    }

    public Room read(int id) throws ParserConfigurationException, IOException, ClassNotFoundException, SAXException {
        return roomRepository.findById(id);
    }

    public boolean update(Room weapon, int id) throws ParserConfigurationException, IOException, SAXException {
        roomRepository.update(weapon,id);
        return true;
    }

    public boolean delete(int id) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        roomRepository.deleteById(id);
        return true;
    }
}
