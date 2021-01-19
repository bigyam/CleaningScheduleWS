package com.CleaningSchedule.CleaningScheduleWS.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {

    @Override
    public boolean test(String s) {
        //TODO: regex validate email
        return true;
    }
}
