package com.CleaningSchedule.CleaningScheduleWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class CleaningScheduleWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleaningScheduleWsApplication.class, args);
	}

}
