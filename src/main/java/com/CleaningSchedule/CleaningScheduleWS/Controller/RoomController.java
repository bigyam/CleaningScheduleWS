package com.CleaningSchedule.CleaningScheduleWS.Controller;

import com.CleaningSchedule.CleaningScheduleWS.DTO.RoomDTO;
import com.CleaningSchedule.CleaningScheduleWS.Service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/room", produces = "application/json")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRooms() {
        List<RoomDTO> response = new ArrayList<>();
        try {
            response = roomService.getAllRooms();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getRoom(@RequestParam(value = "id") Integer roomId) {
        RoomDTO response;
        try {
            response = roomService.getRoom(roomId);
        } catch (Exception e ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    //not tested
    @PostMapping
    public ResponseEntity<Object> addRoom(@RequestBody RoomDTO roomDTO) {
        try {
            roomService.addRoom(roomDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //not tested
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRoom(@PathVariable Integer roomId, @RequestBody RoomDTO roomDTO) {
        try {
            roomService.updateRoom(roomDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //needs to be finished
    @DeleteMapping //need mapping
    public ResponseEntity<Object> deleteRoom(@RequestParam Integer roomId) {
        try {
            roomService.deleteRoomId(roomId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //nov 22 2020: finished service class for room.  need to finish controller class
    //nov 29 2020: fix the return for each method to one return.  Set httpstatus as var in catch.
}
