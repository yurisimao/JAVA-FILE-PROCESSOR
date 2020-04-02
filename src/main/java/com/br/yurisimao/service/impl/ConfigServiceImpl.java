package com.br.yurisimao.service.impl;

import com.br.yurisimao.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {

    @Value("${errors.percentage.rows}")
    private Double errorPercentage;

    @Override
    public int getSkipLimit(final String file) {

        int sizeFile = 0;

        try {
            sizeFile = Files.lines(Paths.get(file), StandardCharsets.UTF_8).filter(x -> StringUtils.isNotEmpty(x)).collect(Collectors.toList()).size();
        } catch (IOException e) {
            log.error("Falha ao obter as linhas do arquivo: " + file);
            log.error(e.getMessage(), e);
        }

        return Double.valueOf((sizeFile * errorPercentage) / 100).intValue();

    }
}


