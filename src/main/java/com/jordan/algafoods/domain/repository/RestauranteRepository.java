package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante cozinha);

    void remover(Restaurante cozinha);
}
