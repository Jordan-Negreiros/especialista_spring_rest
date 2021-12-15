package com.jordan.algafoods.infrastructure.repository;

import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        return entityManager
            .createQuery("from Cozinha", Cozinha.class)
            .getResultList();
    }

    @Override
    public Cozinha buscar(final Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha salvar(final Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Optional.ofNullable(buscar(id))
            .ifPresentOrElse(entityManager::remove,
                () -> {
                    throw new EntidadeNaoEncontradaException(String.format("Cozinha com código %d não encontrado", id));
                });
    }

}
