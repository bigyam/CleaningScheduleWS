package com.CleaningSchedule.CleaningScheduleWS.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
