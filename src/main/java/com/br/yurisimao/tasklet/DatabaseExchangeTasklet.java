package com.br.yurisimao.tasklet;

import com.br.yurisimao.primarydb.entity.PrimaryEntity;
import com.br.yurisimao.primarydb.repository.PrimaryRepository;
import com.br.yurisimao.secondarydb.entity.SecondaryEntity;
import com.br.yurisimao.secondarydb.repository.SecondaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DatabaseExchangeTasklet implements Tasklet {

    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        final String msg = "Executing tasklet..";

        log.info(msg);

        final List<PrimaryEntity> evenList = primaryRepository.findAll().stream().filter(x -> x.getId() % 2 == 0).collect(Collectors.toList());

        final List<SecondaryEntity> oddList = evenList.stream().map(x -> SecondaryEntity.toSecondaryEntity(x)).collect(Collectors.toList());

        primaryRepository.deleteAll(evenList);

        secondaryRepository.saveAll(oddList);

        log.info("Primary List: " + evenList.toString());
        log.info("Secondary List: " + oddList.toString());

        log.info(msg + " done!");

        return RepeatStatus.FINISHED;
    }
}
