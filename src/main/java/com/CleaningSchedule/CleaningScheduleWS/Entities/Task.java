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
public class Task {
    private String taskName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    private List<ScheduleItem> ScheduleItems;
}
