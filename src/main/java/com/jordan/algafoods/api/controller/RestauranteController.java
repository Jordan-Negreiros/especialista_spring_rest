package com.jordan.algafoods.api.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Restaurante;
import com.jordan.algafoods.domain.repository.RestauranteRepository;
import com.jordan.algafoods.domain.service.CadastroRestauranteService;
import com.jordan.algafoods.handlers.BeansHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.jordan.algafoods.handlers.BeansHandler.getNullPropertyNames;

@Slf4j
@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    private final CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable("id") Long id) {
        return Optional.ofNullable(restauranteRepository.buscar(id))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {
        try {
            var restauranteSalvo = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Restaurante restaurante) {
        try {
            cadastroRestauranteService.atualizar(id, restaurante);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarParcial(@PathVariable Long id, @RequestBody Restaurante restaurantePatch) {
        return Optional.ofNullable(restauranteRepository.buscar(id))
            .map(restaurante -> {
                BeanUtils.copyProperties(restaurantePatch, restaurante, getNullPropertyNames(restaurantePatch));
                return atualizar(id, restaurante);
            }).orElse(ResponseEntity.notFound().build());
    }
}
