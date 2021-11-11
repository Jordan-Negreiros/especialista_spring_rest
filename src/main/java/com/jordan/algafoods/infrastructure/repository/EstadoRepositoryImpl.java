package com.jordan.algafoods.infrastructure.repository;

import com.jordan.algafoods.domain.model.Estado;
import com.jordan.algafoods.domain.model.Restaurante;
import com.jordan.algafoods.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        return entityManager
            .createQuery("from Estado", Estado.class)
            .getResultList();
    }

    @Override
    public Estado buscar(final Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Override
    public Estado salvar(final Estado estado) {
        return entityManager.merge(estado);
    }

    @Override
    public void remover(Estado estado) {
        estado = buscar(estado.getId());
        entityManager.remove(estado);
    }

}
