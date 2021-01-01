package com.CleaningSchedule.CleaningScheduleWS.Service;

import com.CleaningSchedule.CleaningScheduleWS.DTO.ScheduleItemDTO;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
import com.CleaningSchedule.CleaningScheduleWS.Entities.ScheduleItem;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Task;
import com.CleaningSchedule.CleaningScheduleWS.Repository.RoomRepository;
import com.CleaningSchedule.CleaningScheduleWS.Repository.ScheduleItemRepository;
import com.CleaningSchedule.CleaningScheduleWS.Repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleItemService {

    private final ScheduleItemRepository scheduleItemRepository;
    private final RoomRepository roomRepository;
    private final TaskRepository taskRepository;

    public List<ScheduleItemDTO> getAllItems() {
        List<ScheduleItem> itemList = new ArrayList<>();
        scheduleItemRepository.findAll().forEach(itemList::add);
        return itemList.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public void addItem(ScheduleItemDTO scheduleItemDTO) throws Exception{
        if (scheduleItemDTO.getId() == null) {
            scheduleItemRepository.save(this.convertDtoToEntity(scheduleItemDTO));
        } else {
            throw new Exception("Is not new item");
        }
    }

    public ScheduleItemDTO updateItem(ScheduleItemDTO scheduleItemDTO) {
        Optional<ScheduleItem> itemOptional = scheduleItemRepository.findById(scheduleItemDTO.getId());
        if (itemOptional.isPresent()) {
            ScheduleItem item = itemOptional.get();
            item.setYearScope(scheduleItemDTO.getYearScope());
            item.setActive(scheduleItemDTO.isActive());
            item.setComplete(scheduleItemDTO.isComplete());
            item.setLastComplete(scheduleItemDTO.getLastComplete());
            if (item.getRoom().getId() != scheduleItemDTO.getRoomId()) {
                Optional<Room> roomOptional = roomRepository.findById(scheduleItemDTO.getRoomId());
                if (roomOptional.isPresent())
                    item.setRoom(roomOptional.get());
            }
            if (item.getTask().getId() != scheduleItemDTO.getTaskId()) {
                Optional<Task> taskOptional = taskRepository.findById(scheduleItemDTO.getTaskId());
                if (taskOptional.isPresent())
                    item.setTask(taskOptional.get());
            }
            scheduleItemRepository.save(item);
            return scheduleItemDTO;
        } else {
            return null;
        }
    }

    public void deleteItemId(Integer itemId) {
        Optional<ScheduleItem> itemOptional = scheduleItemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            ScheduleItem scheduleItem = itemOptional.get();
            scheduleItemRepository.delete(scheduleItem);
        }
    }

    private ScheduleItemDTO convertEntityToDto(ScheduleItem scheduleItemEntity) {
        return ScheduleItemDTO.builder()
                .id(scheduleItemEntity.getId())
                .yearScope(scheduleItemEntity.getYearScope())
                .isActive(scheduleItemEntity.isActive())
                .isComplete(scheduleItemEntity.isComplete())
                .lastComplete(scheduleItemEntity.getLastComplete())
                .roomId(scheduleItemEntity.getRoom().getId())
                .taskId(scheduleItemEntity.getTask().getId())
                .build();
    }

    private ScheduleItem convertDtoToEntity(ScheduleItemDTO scheduleItemDTO) {
        return ScheduleItem.builder()
                .id(scheduleItemDTO.getId())
                .yearScope(scheduleItemDTO.getYearScope())
                .isActive(scheduleItemDTO.isActive())
                .isComplete(scheduleItemDTO.isComplete())
                .lastComplete(scheduleItemDTO.getLastComplete() == null ? null : scheduleItemDTO.getLastComplete())
                .room(roomRepository.findById(scheduleItemDTO.getRoomId()).get())
                .task(taskRepository.findById(scheduleItemDTO.getTaskId()).get())
                .build();
    }
}
