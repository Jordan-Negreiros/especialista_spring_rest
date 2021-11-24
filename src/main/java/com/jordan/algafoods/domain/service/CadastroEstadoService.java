package com.jordan.algafoods.domain.service;

import com.jordan.algafoods.domain.exception.EntidadeEmUsoException;
import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.model.Estado;
import com.jordan.algafoods.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public void atualizar(Long id, Estado estado){
        Optional.ofNullable(estadoRepository.buscar(id))
            .ifPresentOrElse(estadoAtual -> {
                BeanUtils.copyProperties(estado, estadoAtual, "id");
                estadoRepository.salvar(estadoAtual);
            }, () -> {
                throw new EntidadeNaoEncontradaException(String.format("Estado com código %d não encontrado", id));
            });
    }

    public void remover(Long id) {
        try {
            estadoRepository.remover(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removida pois está em uso", id));
        }
    }
}
