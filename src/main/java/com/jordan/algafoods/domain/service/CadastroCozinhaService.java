package com.jordan.algafoods.domain.service;

import com.jordan.algafoods.domain.exception.EntidadeEmUsoException;
import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void atualizar(Long id, Cozinha cozinha) {
        cozinhaRepository.findById(id)
            .ifPresentOrElse(cozinhaAtual -> {
                BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
                cozinhaRepository.save(cozinhaAtual);
            }, () -> {
                throw new EntidadeNaoEncontradaException(String.format("Cozinha com código %d não encontrado", id));
            });
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida pois está em uso", id));
        }
    }
}
