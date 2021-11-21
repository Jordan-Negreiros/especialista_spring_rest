package com.jordan.algafoods.domain.service;

import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Restaurante;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
import com.jordan.algafoods.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        validaCozinhaExiste(restaurante.getCozinha().getId());
        return restauranteRepository.salvar(restaurante);
    }

    public void atualizar(Long id, Restaurante restaurante) {
        Optional.ofNullable(restauranteRepository.buscar(id))
            .ifPresentOrElse(restauranteAtual -> {
                validaCozinhaExiste(restaurante.getCozinha().getId());
                BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
                restauranteRepository.salvar(restauranteAtual);
            }, () -> {
                throw new EntidadeNaoEncontradaException(
                    String.format("Restaurante com Id: %d, não encontrado", id));
            });
    }

    private void validaCozinhaExiste(final Long cozinhaId) {
        var cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                String.format("Não existe cadastro de cozinha com o código %d", cozinhaId));
        }
    }

}
