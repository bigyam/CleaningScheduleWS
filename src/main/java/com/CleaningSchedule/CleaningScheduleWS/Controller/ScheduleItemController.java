package com.CleaningSchedule.CleaningScheduleWS.Controller;

import com.CleaningSchedule.CleaningScheduleWS.DTO.ScheduleItemDTO;
import com.CleaningSchedule.CleaningScheduleWS.Service.ScheduleItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/scheduleitem", produces = "application/json")
@AllArgsConstructor
public class ScheduleItemController {

    private final ScheduleItemService scheduleItemService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllScheduleItems() {
        List<ScheduleItemDTO> response = new ArrayList<>();
        HttpStatus httpStatus;
        try {
            response = scheduleItemService.getAllItems();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }

    /**
     * {
     *         "id": null,
     *         "yearScope": 2,
     *         "lastComplete": null,
     *         "taskId": 3,
     *         "roomId": 3,
     *         "active": true,
     *         "complete": false
     * }
     * @param scheduleItemDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> addScheduleItem(@RequestBody ScheduleItemDTO scheduleItemDTO) {
        HttpStatus httpStatus;
        try {
            scheduleItemService.addItem(scheduleItemDTO);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            System.out.println("e: " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping
    public ResponseEntity<Object> updateScheduleItem(@RequestBody ScheduleItemDTO scheduleItemDTO) {
        HttpStatus httpStatus;
        try {
            scheduleItemService.updateItem(scheduleItemDTO);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteItem(@RequestParam(value = "id") Integer itemId) {
        HttpStatus httpStatus;
        try {
            scheduleItemService.deleteItemId(itemId);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);

    }
}
