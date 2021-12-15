package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();

    Cidade buscar(Long id);

    Cidade salvar(Cidade cozinha);

    void remover(Long id);
}
