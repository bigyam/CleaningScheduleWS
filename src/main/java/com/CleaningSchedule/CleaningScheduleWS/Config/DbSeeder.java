package com.CleaningSchedule.CleaningScheduleWS.Config;

import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
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
        Room room = Room.builder().id(6).roomName("test").complexity(3).build();
        roomRepository.save(room);
    }
}
