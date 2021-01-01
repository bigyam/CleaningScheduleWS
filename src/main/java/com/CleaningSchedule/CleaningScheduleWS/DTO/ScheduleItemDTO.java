package com.CleaningSchedule.CleaningScheduleWS.DTO;

import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleItemDTO {
    private Integer id;
    private Integer yearScope;
    private boolean isActive;
    private boolean isComplete;
    private LocalDateTime lastComplete;
    private Integer taskId;
    private Integer roomId;
}
