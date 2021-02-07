package com.CleaningSchedule.CleaningScheduleWS.Service;

import com.CleaningSchedule.CleaningScheduleWS.DTO.ScheduleItemDTO;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
import com.CleaningSchedule.CleaningScheduleWS.Entities.ScheduleItem;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Task;
import com.CleaningSchedule.CleaningScheduleWS.Repository.RoomRepository;
import com.CleaningSchedule.CleaningScheduleWS.Repository.ScheduleItemRepository;
import com.CleaningSchedule.CleaningScheduleWS.Repository.TaskRepository;
import com.CleaningSchedule.CleaningScheduleWS.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final UserRepository userRepository;

    public List<ScheduleItemDTO> getAllItems() {
        List<ScheduleItem> itemList = new ArrayList<>();
        scheduleItemRepository.findAll().forEach(itemList::add);
        return itemList.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public void modifyScheduleItems(List<ScheduleItemDTO> scheduleItemList) throws Exception{
        Room room;
        Task task;
        ScheduleItem scheduleItem;
        for(ScheduleItemDTO item : scheduleItemList) {
            room = roomRepository.findById(item.getRoom_id()).get();
            task = taskRepository.findById(item.getTask_id()).get();
            scheduleItem = scheduleItemRepository.findByYearScopeAndRoomAndTask(item.getYearScope(), room, task);

            if (scheduleItem == null) {
                item.setIsActive(true);
                scheduleItemRepository.save(this.convertDtoToEntity(item));
            } else {
                scheduleItem.setYearScope(item.getYearScope());
                scheduleItem.setRoom(room);
                scheduleItem.setTask(task);
                scheduleItem.setIsActive(item.getIsActive());
                scheduleItemRepository.save(scheduleItem);
            }
        }
    }

    public void toggleCompleteItem(ScheduleItemDTO scheduleItemDTO) {
        Optional<ScheduleItem> scheduleItemOptional = scheduleItemRepository.findById(scheduleItemDTO.getId());
        if (scheduleItemOptional.isPresent()) {
            ScheduleItem scheduleItem = scheduleItemOptional.get();
            scheduleItem.setIsComplete(scheduleItemDTO.getIsComplete());
            if (scheduleItem.getIsComplete()) {
                scheduleItem.setLastComplete(LocalDateTime.now());
                scheduleItem.setLastCompleteBy(userRepository.findById(scheduleItemDTO.getLastCompletedBy()).get());
            }
            scheduleItemRepository.save(scheduleItem);
        }
    }

    /*public ScheduleItemDTO updateItem(ScheduleItemDTO scheduleItemDTO) {
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
    }*/

    public void deleteItems(List<ScheduleItemDTO> deleteList) {
        Optional<ScheduleItem> optionalScheduleItem;
        ScheduleItem itemEntity;
        for(ScheduleItemDTO item : deleteList) {
            optionalScheduleItem = scheduleItemRepository.findById(item.getId());
            if (optionalScheduleItem.isPresent()) {
                itemEntity = this.convertDtoToEntity(item);
                itemEntity.setIsActive(false);
                scheduleItemRepository.save(itemEntity);
            }
        }
    }

    private ScheduleItemDTO convertEntityToDto(ScheduleItem scheduleItemEntity) {
        return ScheduleItemDTO.builder()
                .id(scheduleItemEntity.getId())
                .yearScope(scheduleItemEntity.getYearScope())
                .isActive(scheduleItemEntity.getIsActive())
                .isComplete(scheduleItemEntity.getIsComplete())
                .lastComplete(scheduleItemEntity.getLastComplete())
                .room_id(scheduleItemEntity.getRoom().getId())
                .task_id(scheduleItemEntity.getTask().getId())
                .lastCompletedBy(scheduleItemEntity.getLastCompleteBy() != null ? scheduleItemEntity.getLastCompleteBy().getId() : null)
                .build();
    }

    private ScheduleItem convertDtoToEntity(ScheduleItemDTO scheduleItemDTO) {
        return ScheduleItem.builder()
                .id(scheduleItemDTO.getId() != null ? scheduleItemDTO.getId() : null)
                .yearScope(scheduleItemDTO.getYearScope())
                .isActive(scheduleItemDTO.getIsActive() != null ? scheduleItemDTO.getIsActive() : null)
                .isComplete(scheduleItemDTO.getIsComplete() != null ? scheduleItemDTO.getIsComplete() : null)
                .lastComplete(scheduleItemDTO.getLastComplete() != null ? scheduleItemDTO.getLastComplete() : null)
                .room(roomRepository.findById(scheduleItemDTO.getRoom_id()).get())
                .task(taskRepository.findById(scheduleItemDTO.getTask_id()).get())
                .lastCompleteBy(userRepository.findById(scheduleItemDTO.getLastCompletedBy()).get())
                .build();
    }
}
