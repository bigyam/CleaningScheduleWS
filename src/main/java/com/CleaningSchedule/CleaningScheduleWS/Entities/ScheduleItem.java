package com.CleaningSchedule.CleaningScheduleWS.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleItem {
    @Id
    @SequenceGenerator(name="SCHEDULEITEM_ID_SEQ", sequenceName = "SCHEDULEITEM_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULEITEM_ID_SEQ")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "year_scope")
    private Integer yearScope;
    @Column(name = "is_Active")
    private boolean isActive;
    @Column(name = "is_Complete")
    private boolean isComplete;
    @Column(name = "last_complete")
    private LocalDateTime lastComplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
