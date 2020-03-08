package com.br.springBatch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobListenerNotification extends JobExecutionListenerSupport {

	@Override
	public void afterJob(JobExecution jobExecution) {

		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("Batch Finalizou com Sucesso!");
		} else {
			log.info("Batch Finalizou com Falha..");
		}

		super.afterJob(jobExecution);
	}

}
