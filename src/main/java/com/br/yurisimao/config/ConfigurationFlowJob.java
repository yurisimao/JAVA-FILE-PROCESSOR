package com.br.yurisimao.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.br.yurisimao.listener.JobListenerNotification;
import com.br.yurisimao.processor.FileProcessor;
import com.br.yurisimao.reader.FileReader;
import com.br.yurisimao.writer.FileWriter;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.br.springBatch.listener", "com.br.springBatch.repository"})
public class ConfigurationFlowJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobListenerNotification listener;

	@Value("${chunck.size}")
	private int chunckSize;
	
	@Value("${max.threads}")
	private int maxThreads;
	
	@Value("${threads.name.prefix}")
	private String threadName;

	@Bean
	public Job flowJob() {
		return jobBuilderFactory.get("flowJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1())
				.end()
				.build();

	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<String, String>chunk(chunckSize)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.taskExecutor(taskExecutor())
				.build();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		final ThreadPoolTaskExecutor threads = new ThreadPoolTaskExecutor();
		threads.setMaxPoolSize(maxThreads);
		threads.setThreadNamePrefix(threadName);
		return threads;
	}

	@Bean
	public FileReader reader() { return new FileReader(); }

	@Bean
	public FileProcessor processor() {
		return new FileProcessor();
	}

	@Bean
	public FileWriter writer() {
		return new FileWriter();
	}

}
