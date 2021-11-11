package com.jordan.algafoods.infrastructure.repository;

import com.jordan.algafoods.domain.model.Cidade;
import com.jordan.algafoods.domain.repository.CidadeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return entityManager
            .createQuery("from Cidade", Cidade.class)
            .getResultList();
    }

    @Override
    public Cidade buscar(final Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Override
    public Cidade salvar(final Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    public void remover(Cidade cidade) {
        cidade = buscar(cidade.getId());
        entityManager.remove(cidade);
    }

}
