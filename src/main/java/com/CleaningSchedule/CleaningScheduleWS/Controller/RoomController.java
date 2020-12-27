package com.CleaningSchedule.CleaningScheduleWS.Controller;

import com.CleaningSchedule.CleaningScheduleWS.DTO.RoomDTO;
import com.CleaningSchedule.CleaningScheduleWS.Service.RoomService;
import lombok.AllArgsConstructor;
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
        HttpStatus httpStatus;
        try {
            response = roomService.getAllRooms();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }

    //TODO: room not found error log?
    @GetMapping
    public ResponseEntity<Object> getRoom(@RequestParam(value = "id") Integer roomId) {
        RoomDTO response;
        HttpStatus httpStatus;
        try {
            response = roomService.getRoom(roomId);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            response = null;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }

    /**
     * null roomId = new room
     *
     * @param roomDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> addRoom(@RequestBody RoomDTO roomDTO) {
        HttpStatus httpStatus;
        try {
            roomService.addRoom(roomDTO);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
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
        HttpStatus httpStatus;
        try {
            roomService.updateRoom(roomDTO);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteRoom(@RequestParam(value = "id") Integer roomId) {
        HttpStatus httpStatus;
        try {
            roomService.deleteRoomId(roomId);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
