package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRespository extends JpaRepository<FormaPagamento, Long> {
}
