package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado cozinha);

    void remover(Estado cozinha);
}
