package com.CleaningSchedule.CleaningScheduleWS.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaskDTO {
    private Integer id;
    private String name;
}
