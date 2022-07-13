package com.nikitakozhemyako.shema_etaja1.repository;

import com.nikitakozhemyako.shema_etaja1.domain.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomRepositoryTest {
    RoomRepository roomRepository;
    @BeforeEach
    void init() {
        roomRepository = new RoomRepository();
    }

    @Test
    void roomFindAll() throws ParserConfigurationException, IOException, SAXException {
        RoomRepository roomRepository = new RoomRepository();
        List<Room> all = roomRepository.findAll();
        roomRepository.findAll();
        all.forEach(System.out::println);
        Room r = new Room();

    }

    @Test
    void save() throws ParserConfigurationException, TransformerException, IOException, SAXException {
       roomRepository.save(new Room(1, 2, "lol", 34.0,8,4,true));
       roomRepository.save(new Room(1, 7, "lol", 34.0,8,4,true));
    }
}