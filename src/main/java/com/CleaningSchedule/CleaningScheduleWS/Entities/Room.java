package com.CleaningSchedule.CleaningScheduleWS.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @SequenceGenerator(name="ROOM_ID_SEQ", sequenceName = "ROOM_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_ID_SEQ")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "room_name", length = 100)
    private String roomName;
    @Column(name = "complexity")
    private Integer complexity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<ScheduleItem> ScheduleItems;
    //id, room_name, complexity
}
