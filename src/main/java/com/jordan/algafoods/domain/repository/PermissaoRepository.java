package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> listar();

    Permissao buscar(Long id);

    Permissao salvar(Permissao permissao);

    void remover(Permissao permissao);
}
