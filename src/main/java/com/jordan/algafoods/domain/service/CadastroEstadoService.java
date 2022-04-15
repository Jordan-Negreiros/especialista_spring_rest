package com.jordan.algafoods.domain.service;

import com.jordan.algafoods.domain.exception.EntidadeEmUsoException;
import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Estado;
import com.jordan.algafoods.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroEstadoService {

    private final EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void atualizar(Long id, Estado estado) {
        estadoRepository.findById(id).ifPresentOrElse(estadoAtual -> {
            BeanUtils.copyProperties(estado, estadoAtual, "id");
            estadoRepository.save(estadoAtual);
        }, () -> {
            throw new EntidadeNaoEncontradaException(String.format("Estado com código %d não encontrado", id));
        });
    }

    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removida pois está em uso", id));
        }
    }
}
