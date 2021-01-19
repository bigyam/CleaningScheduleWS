package com.CleaningSchedule.CleaningScheduleWS.Controller;

import com.CleaningSchedule.CleaningScheduleWS.DTO.RegistrationDTO;
import com.CleaningSchedule.CleaningScheduleWS.Service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationDTO request) {
        return registrationService.register(request);
    }

    @GetMapping
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
