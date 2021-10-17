package com.jordan.algafoods.infrastructure.repository;

import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        return entityManager
            .createQuery("from Cozinha",Cozinha.class)
            .getResultList();
    }

    @Override
    public Cozinha buscar(final Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Override
    public Cozinha salvar(final Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Override
    public void remover(final Cozinha cozinha) {
        entityManager.remove(cozinha);
    }

}