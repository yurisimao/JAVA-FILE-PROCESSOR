package com.br.springBatch.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.br.springBatch.config.ConfigurationFlowJob;

@SpringBootApplication
@ComponentScan(basePackageClasses = { ConfigurationFlowJob.class })
public class Application {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		final int exitCode = SpringApplication.exit(context);
		System.exit(exitCode);
	}

}
