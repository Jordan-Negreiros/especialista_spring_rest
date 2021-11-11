package com.jordan.algafoods.infrastructure.repository;

import com.jordan.algafoods.domain.model.FormaPagamento;
import com.jordan.algafoods.domain.repository.FormaPagamentoRespository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRespository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FormaPagamento> listar() {
        return entityManager
            .createQuery("from FormaPagamento", FormaPagamento.class)
            .getResultList();
    }

    @Override
    public FormaPagamento buscar(final Long id) {
        return entityManager.find(FormaPagamento.class, id);
    }

    @Override
    public FormaPagamento salvar(final FormaPagamento formaPagamento) {
        return entityManager.merge(formaPagamento);
    }

    @Override
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = buscar(formaPagamento.getId());
        entityManager.remove(formaPagamento);
    }

}
