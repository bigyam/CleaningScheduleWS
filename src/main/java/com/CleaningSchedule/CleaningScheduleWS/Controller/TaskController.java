package com.CleaningSchedule.CleaningScheduleWS.Controller;

import com.CleaningSchedule.CleaningScheduleWS.DTO.TaskDTO;
import com.CleaningSchedule.CleaningScheduleWS.Service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/task", produces = "application/json")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllTasks() {
        List<TaskDTO> response = new ArrayList<>();
        HttpStatus httpStatus;
        try {
            response = taskService.getAllTasks();
            httpStatus = HttpStatus.OK;
        } catch (Exception e ) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }

    /**
     * {
     *     "id": null,
     *     "name": "test"
     * }
     * @param taskDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> addTask(@RequestBody TaskDTO taskDTO) {
        HttpStatus httpStatus;
        try {
            taskService.addTask(taskDTO);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping
    public ResponseEntity<Object> updateTask(@RequestBody TaskDTO taskDTO) {
        HttpStatus httpStatus;
        try {
            taskService.updateTask(taskDTO);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteTask(@RequestParam(value = "id") Integer taskId) {
        HttpStatus httpStatus;
        try {
            taskService.deleteTaskId(taskId);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
