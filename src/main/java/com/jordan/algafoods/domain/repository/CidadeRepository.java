package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
