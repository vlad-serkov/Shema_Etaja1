package com.nikitakozhemyako.shema_etaja1.controllers;

import com.nikitakozhemyako.shema_etaja1.domain.Room;
import com.nikitakozhemyako.shema_etaja1.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;


@RestController
@ResponseBody
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService weaponService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Room weapon) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        weaponService.creat(weapon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/read")
    public List<Room> read() throws ParserConfigurationException, IOException, SAXException {
        return weaponService.readAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Room>>read(@PathVariable(name="id") int id) throws ParserConfigurationException, IOException, ClassNotFoundException, SAXException {
        final Room weapon=weaponService.read(id);

        return weapon!=null
                ? new  ResponseEntity<>(HttpStatus.OK)
                : new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Room room) throws ParserConfigurationException, IOException, SAXException {
        final boolean updated = weaponService.update(room, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        final boolean deleted = weaponService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
