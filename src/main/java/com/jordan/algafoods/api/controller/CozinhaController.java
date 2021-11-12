package com.jordan.algafoods.api.controller;

import com.jordan.algafoods.api.model.CozinhasXmlWrapper;
import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
    value = "/cozinhas",
    produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
    }

    @GetMapping("/{id}")
    // @ResponseStatus(HttpStatus.OK) -> Define o HttpStatus de Retornado pela requisição
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        var cozinha = cozinhaRepository.buscar(id);

        return Optional.ofNullable(cozinha)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

        // return ResponseEntity.status(HttpStatus.OK).body(cozinha); -> método mais completo
        // return ResponseEntity.ok(cozinha); -> método reduzido

        // var headers = new HttpHeaders();
        // headers.add("headerName", "headerValue");
        //
        // // Exemplo adicionando header
        // return ResponseEntity
        //     .status(HttpStatus.OK)
        //     .headers(headers)
        //     .body(cozinha);
    }


}
