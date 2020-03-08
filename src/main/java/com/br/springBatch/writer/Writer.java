package com.br.springBatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {

		items.forEach(x -> System.out.println("Writer - " + x));

	}

}
