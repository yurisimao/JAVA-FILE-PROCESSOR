package com.br.springBatch.processor;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		
		System.out.println("Processor - " + item);
		
		return item;
	}

}
