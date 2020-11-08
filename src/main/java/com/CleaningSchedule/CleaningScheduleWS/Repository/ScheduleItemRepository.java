package com.CleaningSchedule.CleaningScheduleWS.Repository;

import com.CleaningSchedule.CleaningScheduleWS.Entities.ScheduleItem;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ScheduleItemRepository extends CrudRepository<ScheduleItem, UUID> {
}
