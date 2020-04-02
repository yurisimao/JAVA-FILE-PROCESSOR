package com.br.yurisimao.config;

import com.br.yurisimao.tasklet.DatabaseExchangeTasklet;
import com.br.yurisimao.domain.PrimaryDomain;
import com.br.yurisimao.exception.LineUnexpectedException;
import com.br.yurisimao.listener.JobListenerNotification;
import com.br.yurisimao.processor.FileProcessor;
import com.br.yurisimao.reader.FileReader;
import com.br.yurisimao.service.ConfigService;
import com.br.yurisimao.writer.FileWriter;
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
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import static com.br.yurisimao.constants.Constants.*;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.br.yurisimao.listener",
        "com.br.yurisimao.service",
        "com.br.yurisimao.primarydb",
        "com.br.yurisimao.secondarydb"})
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobListenerNotification listener;

    @Autowired
    private ConfigService configService;

    @Value("${input.file}")
    private String inputFile;

    @Value("${chunck.size}")
    private int chunckSize;

    @Value("${max.threads}")
    private int maxThreads;

    @Value("${threads.name.prefix}")
    private String threadName;

    @Bean
    public Job flowJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .next(step2())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get(STEP1_NAME)
                .<PrimaryDomain, PrimaryDomain>chunk(chunckSize)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .skip(LineUnexpectedException.class)
                .skipLimit(configService.getSkipLimit(inputFile))
                .taskExecutor(taskExecutor())
                .throttleLimit(maxThreads)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory
                .get(STEP2_NAME)
                .tasklet(tasklet())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        final SimpleAsyncTaskExecutor threads = new SimpleAsyncTaskExecutor();
        threads.setConcurrencyLimit(maxThreads);
        threads.setThreadNamePrefix(threadName);
        return threads;
    }

    @Bean
    public FileReader reader() {
        return new FileReader(inputFile);
    }

    @Bean
    public FileProcessor processor() { return new FileProcessor(); }

    @Bean
    public FileWriter writer() {
        return new FileWriter();
    }

    @Bean
    public DatabaseExchangeTasklet tasklet() { return new DatabaseExchangeTasklet(); }

}

