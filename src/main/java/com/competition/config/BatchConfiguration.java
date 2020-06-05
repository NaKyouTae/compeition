package com.competition.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job Job() {
		return jobBuilderFactory.get("Job")
				.start(Step())
				.build();
	}
	
	@Bean
	public Step Step() {
		return stepBuilderFactory.get("Step")
				.tasklet((con, chunk) -> {
					System.out.println("Step Start");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
}
