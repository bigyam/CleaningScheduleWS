package com.CleaningSchedule.CleaningScheduleWS.DTO;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RoomDTO {
    private Integer id;
    private String name;
    private Integer complexity;
}
