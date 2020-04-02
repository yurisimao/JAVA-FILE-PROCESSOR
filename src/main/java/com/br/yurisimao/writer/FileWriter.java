package com.br.yurisimao.writer;

import java.util.List;
import java.util.stream.Collectors;

import com.br.yurisimao.domain.PrimaryDomain;
import com.br.yurisimao.primarydb.entity.PrimaryEntity;
import com.br.yurisimao.primarydb.repository.PrimaryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FileWriter implements ItemWriter<PrimaryDomain> {

	@Autowired
	private PrimaryRepository repo;

	@Override
	public void write(@NotNull final List<? extends PrimaryDomain> domain) throws Exception {

		final List<PrimaryEntity> entities = domain.stream().map(x -> PrimaryEntity.toEntity(x)).collect(Collectors.toList());
		log.info("Saving itens.. " + entities);
		repo.saveAll(entities);
	}
}
