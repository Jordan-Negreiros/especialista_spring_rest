package com.jordan.algafoods.infrastructure.repository;

import com.jordan.algafoods.domain.model.Restaurante;
import com.jordan.algafoods.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> listar() {
        return entityManager
            .createQuery("from Restaurante",Restaurante.class)
            .getResultList();
    }

    @Override
    public Restaurante buscar(final Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Override
    public Restaurante salvar(final Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Override
    public void remover(Restaurante restaurante) {
        restaurante = buscar(restaurante.getId());
        entityManager.remove(restaurante);
    }
}
