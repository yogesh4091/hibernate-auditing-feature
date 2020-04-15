package com.frankmoley.webservice;

import com.frankmoley.entity.Room;
import com.frankmoley.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoomController {
  @Autowired
  private RoomRepository repository;

  @RequestMapping(value = "/rooms", method = RequestMethod.GET)
  List<Room> findAll(@RequestParam(required = false) String roomNumber) {
    List<Room> rooms = new ArrayList<>();
    if (null == roomNumber) {
      Iterable<Room> results = this.repository.findAll();
      results.forEach(room -> {
        rooms.add(room);
      });
    } else {
      Room room = this.repository.findByNumber(roomNumber);
      if (null != room) {
        rooms.add(room);
      }
    }
    return rooms;
  }

  @RequestMapping(value = "/rooms", method = RequestMethod.POST)
  Room persistRecord() {
    Room room = new Room();
    room.setBedInfo("BI");
    room.setName("ABC");
    room.setNumber("123");
    room =  repository.save(room);

    room.setName("XYZ");
    repository.save(room);

    return room;
  }
}
