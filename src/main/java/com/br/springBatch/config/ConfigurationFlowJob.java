package com.br.springBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.br.springBatch.listener.JobListenerNotification;
import com.br.springBatch.processor.Processor;
import com.br.springBatch.reader.Reader;
import com.br.springBatch.writer.Writer;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.br.springBatch.listener")
public class ConfigurationFlowJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobListenerNotification listener;

	@Value("${chunck.size}")
	private int chunckSize;

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
				.build();
	}

	@Bean
	public Reader reader() {
		return new Reader();
	}

	@Bean
	public Processor processor() {
		return new Processor();
	}

	@Bean
	public Writer writer() {
		return new Writer();
	}

}