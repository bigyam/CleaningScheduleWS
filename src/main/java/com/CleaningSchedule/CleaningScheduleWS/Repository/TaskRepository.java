package com.CleaningSchedule.CleaningScheduleWS.Repository;

import com.CleaningSchedule.CleaningScheduleWS.Entities.Task;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, Integer>{
}
