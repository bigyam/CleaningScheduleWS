package com.CleaningSchedule.CleaningScheduleWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class CleaningScheduleWsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CleaningScheduleWsApplication.class, args);
	}

}
