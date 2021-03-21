package com.CleaningSchedule.CleaningScheduleWS.Config;

import com.CleaningSchedule.CleaningScheduleWS.Entities.*;
import com.CleaningSchedule.CleaningScheduleWS.Repository.RoleRepository;
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
    private RoleRepository roleRepository;

    @Autowired
    public DbSeeder ( RoomRepository roomRepository, ScheduleItemRepository scheduleItemRepository, TaskRepository taskRepository, RoleRepository roleRepository) {
        this.roomRepository = roomRepository;
        this.scheduleItemRepository = scheduleItemRepository;
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seed();
    }

    private void seed() {
        Role roleUser = Role.builder().id(1).name(ERole.USER).build();
        Role roleAdmin = Role.builder().id(2).name(ERole.ADMIN).build();
        Room room = Room.builder().id(6).roomName("test").complexity(3).build();
        Task task = Task.builder().id(1).taskName("test").build();
        //ScheduleItem item = ScheduleItem.builder().id(1).isActive(true).isComplete(false).lastComplete(null).yearScope(2).room(room).task(task).build();
        taskRepository.save(task);
        roomRepository.save(room);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        //scheduleItemRepository.save(item);
    }
}
