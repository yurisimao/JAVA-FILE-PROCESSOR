package com.br.yurisimao.primarydb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.yurisimao.primarydb.entity.PrimaryEntity;

@Repository
public interface PrimaryRepository extends JpaRepository<PrimaryEntity, Long> {

}

