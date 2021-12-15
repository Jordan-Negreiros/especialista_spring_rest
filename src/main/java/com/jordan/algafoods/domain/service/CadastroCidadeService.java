package com.jordan.algafoods.domain.service;

import com.jordan.algafoods.domain.exception.EntidadeEmUsoException;
import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Cidade;
import com.jordan.algafoods.domain.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;

    public Cidade salvar(final Cidade cidade) {
        return cidadeRepository.salvar(cidade);
    }

    public void atualizar(final Long id, final Cidade cidade) {
        Optional.ofNullable(cidadeRepository.buscar(id))
            .ifPresentOrElse(cidadeAtual -> {
                BeanUtils.copyProperties(cidade, cidadeAtual, "id");
                cidadeRepository.salvar(cidadeAtual);
            }, () -> {
              throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não pode ser removida pois está em uso", id));
        });
    }

    public void remover(final Long id) {
        try {
            cidadeRepository.remover(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cidade de código %d não pode ser removida pois está em uso", id));
        }
    }

}
