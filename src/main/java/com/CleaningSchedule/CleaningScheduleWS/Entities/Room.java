package com.CleaningSchedule.CleaningScheduleWS.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room extends EntityWithUUID{

    private String roomName;
    private Integer complexity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<ScheduleItem> ScheduleItems;
    //id, room_name, complexity
}
