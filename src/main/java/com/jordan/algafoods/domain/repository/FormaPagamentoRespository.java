package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRespository {
    List<FormaPagamento> listar();

    FormaPagamento buscar(Long id);

    FormaPagamento salvar(FormaPagamento formaPagamento);

    void remover(FormaPagamento formaPagamento);
}
