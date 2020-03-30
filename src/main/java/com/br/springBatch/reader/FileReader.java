package com.br.springBatch.reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

public class FileReader implements ItemReader<String> {

	@Value("${input.file}")
	private String inputFile;

	private int count;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		final List<String> lines = Files.lines(Paths.get(inputFile)).filter(x -> StringUtils.isNotEmpty(x))
				.collect(Collectors.toList());

		if (count < lines.size()) {

			final String line = lines.get(count);
			count++;

			return line;

		}

		return null;
	}

}
