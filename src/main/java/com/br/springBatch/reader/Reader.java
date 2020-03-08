package com.br.springBatch.reader;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

public class Reader implements ItemReader<String> {

	@Value("${input.file}")
	private String inputFile;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		final FileInputStream inputStream = new FileInputStream(new File(inputFile));

		final Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8);

		while (sc.hasNextLine()) {
			return sc.nextLine();
		}

		inputStream.close();
		sc.close();

		return null;
	}

}
