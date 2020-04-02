package com.br.yurisimao.app;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.br.yurisimao.config.BatchConfig;

import java.time.Duration;
import java.time.Instant;

import static com.br.yurisimao.constants.Constants.EXECUTION_TIME;


@Slf4j
@SpringBootApplication
@ComponentScan(basePackageClasses = {BatchConfig.class})
public class Application {

    public static void main(String[] args) {
        final Instant start = Instant.now();
        final ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        final int exitCode = SpringApplication.exit(context);

        final Instant end = Instant.now();
        log.info(getExecutionTime(start, end));
        log.info("Exit code: " + exitCode);
        System.exit(exitCode);
    }

    @NotNull
    private static String getExecutionTime(final Instant start, final Instant end) {
        final Duration duration = Duration.between(start, end);
        return EXECUTION_TIME + String.format("%02dhh:%02dmm:%02dss.%03dms", duration.toHours(), duration.toMinutes(), duration.toSeconds(), duration.toMillis());
    }

}






