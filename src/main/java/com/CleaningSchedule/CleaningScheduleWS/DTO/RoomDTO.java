package com.CleaningSchedule.CleaningScheduleWS.DTO;

import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {
    private Integer id;
    private String name;
    private Integer complexity;
}
