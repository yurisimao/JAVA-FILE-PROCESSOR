package com.br.springBatch.App;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.br.springBatch.config.ConfigurationFlowJob;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(basePackageClasses = {ConfigurationFlowJob.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
