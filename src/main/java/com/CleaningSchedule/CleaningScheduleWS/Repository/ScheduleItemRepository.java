package com.CleaningSchedule.CleaningScheduleWS.Repository;

import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
import com.CleaningSchedule.CleaningScheduleWS.Entities.ScheduleItem;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleItemRepository extends CrudRepository<ScheduleItem, Integer> {
    ScheduleItem findByYearScopeAndRoomAndTask(Integer yearScope, Room room, Task task);
}
