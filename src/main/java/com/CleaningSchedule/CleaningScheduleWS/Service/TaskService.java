package com.CleaningSchedule.CleaningScheduleWS.Service;

import com.CleaningSchedule.CleaningScheduleWS.DTO.TaskDTO;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Task;
import com.CleaningSchedule.CleaningScheduleWS.Repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDTO> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        taskRepository.findAll().forEach(taskList::add);
        return taskList.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public void addTask(TaskDTO taskDTO) throws Exception {
        if (taskDTO.getId() == null) {
            taskRepository.save(this.convertDtoToEntity(taskDTO));
        } else {
            throw new Exception("Is not new task");
        }
    }

    public TaskDTO updateTask(TaskDTO taskDTO) {
        Optional<Task> taskOptional = taskRepository.findById(taskDTO.getId());
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTaskName(taskDTO.getName());
            taskRepository.save(task);
            return taskDTO;
        } else {
            return null;
        }
    }

    public void deleteTaskId(Integer taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            taskRepository.delete(task);
        }
    }

    public TaskDTO convertEntityToDto(Task taskEntity) {
        return TaskDTO.builder()
                .id(taskEntity.getId())
                .name(taskEntity.getTaskName())
                .build();
    }

    public Task convertDtoToEntity(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .taskName(taskDTO.getName())
                .build();
    }
}
