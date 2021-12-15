package com.jordan.algafoods.infrastructure.repository;

import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Cidade;
import com.jordan.algafoods.domain.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

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
    @Transactional
    public Cidade salvar(final Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(final Long id) {
        Optional.ofNullable(buscar(id))
            .ifPresentOrElse(entityManager::remove,
                () -> {
                    throw new EntidadeNaoEncontradaException(String.format("Cidade com código %d não encontrado", id));
                });
    }

}
