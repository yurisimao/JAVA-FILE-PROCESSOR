package com.br.projeto.App;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GeneratorFile {

	private static final String EXTENSION = ".txt";

	private static final String DATE_MASK = "ddMMyyyyHHmmss";

	private static final String PATH_FILE = "D:\\Projetos\\Desenvolvimento\\Java\\MASSA-TESTE\\FILE-PROCESSOR\\Input";

	private static final String FILENAME = "ARQUIVO_PROCESSOR_";

	private static final int MAX_SIZE = 10000;

	private static final String DELIMITADOR = "|";

	public static void main(String[] args) throws IOException {

		String msg = "Gerando conteudo do arquivo.. ";

		System.out.println(msg);

		final List<String> lines = new ArrayList<>();

		int count = 0;

		final String pathFile = PATH_FILE + File.separator + FILENAME
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_MASK)) + EXTENSION;

		while (count <= MAX_SIZE) {

			final String content = String.valueOf(count) + DELIMITADOR + UUID.randomUUID() + DELIMITADOR + "teste"
					+ String.valueOf(count);

			lines.add(content);

			count++;

		}

		Files.write(Paths.get(pathFile), lines, StandardOpenOption.CREATE);

		System.out.println(msg + " Done!");

	}

}
