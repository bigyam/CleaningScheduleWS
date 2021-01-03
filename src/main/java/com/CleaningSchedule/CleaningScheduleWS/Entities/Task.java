package com.CleaningSchedule.CleaningScheduleWS.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Task {
    @Id
    @SequenceGenerator(name="TASK_ID_SEQ", sequenceName = "TASK_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ID_SEQ")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "task_name")
    private String taskName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    private List<ScheduleItem> ScheduleItems;
}
