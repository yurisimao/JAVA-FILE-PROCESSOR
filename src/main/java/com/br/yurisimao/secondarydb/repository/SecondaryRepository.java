package com.br.yurisimao.secondarydb.repository;

import com.br.yurisimao.secondarydb.entity.SecondaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryRepository extends JpaRepository<SecondaryEntity, Long> {
}
