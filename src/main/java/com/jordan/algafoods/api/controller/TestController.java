package com.jordan.algafoods.api.controller;

import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.model.Restaurante;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
import com.jordan.algafoods.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class TestController {

    private final CozinhaRepository cozinhaRepository;
    private final RestauranteRepository restauranteRepository;

    @GetMapping("/cozinha/nome/{nome}")
    public List<Cozinha> findCozinhaByName(@PathVariable String nome) {
        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping("/restaurante/taxa")
    public List<Restaurante> findRestaurantePorTaxaFrete(BigDecimal taxa_inicial, BigDecimal taxa_final) {
        return restauranteRepository.findByTaxaFreteBetween(taxa_inicial, taxa_final);
    }

    @GetMapping("/restaurante")
    public List<Restaurante> findRestaurantePorTaxaFrete(String nome, Long cozinhaId) {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }
}
