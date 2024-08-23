package com.adp.JobTraq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JobTraqApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobTraqApplication.class, args);
	}

}
