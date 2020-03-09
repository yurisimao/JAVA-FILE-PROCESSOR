package com.br.springBatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Writer implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {

		items.forEach(x -> log.info("Writer - " + x));

	}

}
