package com.prv.art.tasks.timetracker;

import com.prv.art.tasks.timetracker.util.mappers.TaskMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimetrackerApplicationConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return TaskMapper.getInstance();
	}

}
