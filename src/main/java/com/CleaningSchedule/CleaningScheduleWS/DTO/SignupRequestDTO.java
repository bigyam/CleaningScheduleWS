package com.CleaningSchedule.CleaningScheduleWS.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequestDTO {
    private String username;
    private Set<String> roles;
    private String email;
    private String password;
}
