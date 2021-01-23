package com.CleaningSchedule.CleaningScheduleWS.Repository;

import java.util.Optional;

import com.CleaningSchedule.CleaningScheduleWS.Entities.Role;
import com.CleaningSchedule.CleaningScheduleWS.Entities.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
