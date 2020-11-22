package com.CleaningSchedule.CleaningScheduleWS.Repository;

import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface RoomRepository extends CrudRepository<Room, Integer> {
}
