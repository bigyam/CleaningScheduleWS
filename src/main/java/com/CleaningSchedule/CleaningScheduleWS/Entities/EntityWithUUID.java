package com.CleaningSchedule.CleaningScheduleWS.Entities;

import org.hibernate.annotations.Type;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import java.util.UUID;

@MappedSuperclass
public class EntityWithUUID {

    @Id @Type(type = "pg-uuid")
    private UUID id;

    public EntityWithUUID() {
        this.id = UUID.randomUUID();
    }
}
