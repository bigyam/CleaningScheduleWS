package com.CleaningSchedule.CleaningScheduleWS.Config;

import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
import com.CleaningSchedule.CleaningScheduleWS.Entities.ScheduleItem;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Task;
import com.CleaningSchedule.CleaningScheduleWS.Repository.RoomRepository;
import com.CleaningSchedule.CleaningScheduleWS.Repository.ScheduleItemRepository;
import com.CleaningSchedule.CleaningScheduleWS.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DbSeeder {

    private RoomRepository roomRepository;
    private ScheduleItemRepository scheduleItemRepository;
    private TaskRepository taskRepository;

    @Autowired
    public DbSeeder ( RoomRepository roomRepository, ScheduleItemRepository scheduleItemRepository, TaskRepository taskRepository) {
        this.roomRepository = roomRepository;
        this.scheduleItemRepository = scheduleItemRepository;
        this.taskRepository = taskRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seed();
    }

    private void seed() {
        /*//Room room = Room.builder().id(6).roomName("test").complexity(3).build();
        //Task task = Task.builder().id(1).taskName("test").build();
        //ScheduleItem item = ScheduleItem.builder().id(1).isActive(true).isComplete(false).lastComplete(null).yearScope(2).room(room).task(task).build();
        taskRepository.save(task);
        roomRepository.save(room);
        //scheduleItemRepository.save(item);*/
    }
}
