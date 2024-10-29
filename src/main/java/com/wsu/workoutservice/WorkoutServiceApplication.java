package com.wsu.workoutservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// Line to get rid of when you want to try to connect to the DB
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

// LINE to replace with
@SpringBootApplication
public class WorkoutServiceApplication {

	/**
	 * SpringBoot Starter method on embedded tomcat server
	 * @param args - Allow to pass String array JVM arguments to set value dynamically during runtime
	 */
	public static void main(String[] args) {
		SpringApplication.run(WorkoutServiceApplication.class, args);
	}

}
