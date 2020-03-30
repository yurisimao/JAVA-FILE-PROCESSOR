package com.br.yurisimao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.yurisimao.entity.FileEntity;

/**
 * The Interface FileRepository.
 * @author Yuri Simão
 * @since 08/03/2020
 */
@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

}

