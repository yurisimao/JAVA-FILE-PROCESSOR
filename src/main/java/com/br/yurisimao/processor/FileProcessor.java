package com.br.yurisimao.processor;

import com.br.yurisimao.domain.PrimaryDomain;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.ItemProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileProcessor implements ItemProcessor<PrimaryDomain, PrimaryDomain> {

    @Override
    public PrimaryDomain process(@NotNull final PrimaryDomain domain) throws Exception {

        if (domain.getPosition() % 100 == 0) {

            log.info("Found! " + domain);

            return domain;
        }

        return null;
    }
}


