package com.br.springBatch.writer;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileWriter implements ItemWriter<String> {

	@Override
	public void write(@NotNull List<? extends String> items) throws Exception {

		items.forEach(x -> log.info("Writer - " + x));

	}

}
