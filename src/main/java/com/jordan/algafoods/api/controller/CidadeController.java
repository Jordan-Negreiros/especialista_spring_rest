package com.jordan.algafoods.api.controller;

import com.jordan.algafoods.domain.exception.EntidadeEmUsoException;
import com.jordan.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.jordan.algafoods.domain.model.Cidade;
import com.jordan.algafoods.domain.repository.CidadeRepository;
import com.jordan.algafoods.domain.service.CadastroCidadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(
    value = "/cidades",
    produces = {
        MediaType.APPLICATION_JSON_VALUE
    }
)
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeRepository cidadeRepository;
    private final CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable Long id) {
        return cidadeRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(cadastroCidadeService.salvar(cidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        cadastroCidadeService.atualizar(id, cidade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            cadastroCidadeService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
