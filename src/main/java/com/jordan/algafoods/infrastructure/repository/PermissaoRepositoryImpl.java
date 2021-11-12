package com.jordan.algafoods.infrastructure.repository;

import com.jordan.algafoods.domain.model.Permissao;
import com.jordan.algafoods.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> listar() {
        return entityManager
            .createQuery("from Permissao", Permissao.class)
            .getResultList();
    }

    @Override
    public Permissao buscar(final Long id) {
        return entityManager.find(Permissao.class, id);
    }

    @Override
    public Permissao salvar(final Permissao permissao) {
        return entityManager.merge(permissao);
    }

    @Override
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        entityManager.remove(permissao);
    }

}