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
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * null roomId = new room
     *
     * @param roomDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> addRoom(@RequestBody RoomDTO roomDTO) {
        try {
            roomService.addRoom(roomDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Object to send
     * {
     * "id": 3,
     * "name": "test",
     * "complexity": 1
     * }
     *
     * @param roomDTO
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> updateRoom(@RequestBody RoomDTO roomDTO) {
        try {
            roomService.updateRoom(roomDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteRoom(@RequestParam(value = "id") Integer roomId) {
        try {
            roomService.deleteRoomId(roomId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
    //nov 29 2020: fix the return for each method to one return.  Set httpstatus as var in catch.
    //Dec 24 2020: controller class endpoints working for room, primiliary testing OK.  still need to do nov 29 item.
