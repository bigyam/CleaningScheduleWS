package com.CleaningSchedule.CleaningScheduleWS.DTO;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ScheduleItemDTO {
    private Integer id;
    private Integer yearScope;
    private Boolean isActive;
    private Boolean isComplete;
    private LocalDateTime lastComplete;
    private Integer task_id;
    private Integer room_id;
    private Long lastCompletedBy;
}
