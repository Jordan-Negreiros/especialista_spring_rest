package com.jordan.algafoods.domain.service;

import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Restaurante;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
import com.jordan.algafoods.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        validaCozinhaExiste(restaurante.getCozinha().getId());
        return restauranteRepository.save(restaurante);
    }

    public void atualizar(Long id, Restaurante restaurante) {
        restauranteRepository.findById(id).ifPresentOrElse(restauranteAtual -> {
            validaCozinhaExiste(restaurante.getCozinha().getId());
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            restauranteRepository.save(restauranteAtual);
        }, () -> {
            throw new EntidadeNaoEncontradaException(
                String.format("Restaurante com Id: %d, não encontrado", id));
        });
    }

    private void validaCozinhaExiste(final Long cozinhaId) {
        var cozinha = cozinhaRepository.findById(cozinhaId).isEmpty();

        if (cozinha) {
            throw new EntidadeNaoEncontradaException(
                String.format("Não existe cadastro de cozinha com o código %d", cozinhaId));
        }
    }

}
