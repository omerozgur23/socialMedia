package com.socialMedia.core.utilities.config.quartz;

import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfig {

	@Bean
	public JobFactory jobFactory() {
		// SpringBeanJobFactory, Spring tarafından yönetilen Job'ları oluşturur.
		SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean scheduler(JobFactory jobFactory) {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setJobFactory(jobFactory);
		return schedulerFactory;
	}
}
