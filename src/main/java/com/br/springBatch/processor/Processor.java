package com.br.springBatch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Processor implements ItemProcessor<String, String> {
	
	@Value("${input.file}")
	private String inputFile;

	@Override
	public String process(String item) throws Exception {
		
		log.info("Processor - " + item);
		
		return item;
	}

}
