package com.jordan.algafoods.api.controller;

import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class TestController {

    private final CozinhaRepository cozinhaRepository;

    @GetMapping("/cozinha/nome/{nome}")
    public List<Cozinha> findCozinhaByName(@PathVariable String nome) {
        return cozinhaRepository.findByNomeContaining(nome);
    }
}
