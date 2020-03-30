package com.br.yurisimao.processor;

import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileProcessor implements ItemProcessor<String, String> {
	
	@Value("${input.file}")
	private String inputFile;

	@Override
	public String process(@NotNull String item) throws Exception {
		
		final int number = Integer.parseInt(item.split("\\|")[0]);
		
		if (number % 100 == 0) {
			
			log.info("Found! " + item);
			
			return item;
		}
		
		return null;
		
	}

}
