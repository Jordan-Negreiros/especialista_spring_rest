package com.jordan.algafoods.jpa;

import com.jordan.algafoods.AlgafoodsApplication;
import com.jordan.algafoods.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class RemoveCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodsApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        var cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");

        var cozinha2 = new Cozinha();
        cozinha2.setNome("Japonesa");

        var cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        cadastroCozinha.salvar(cozinha1);
        cadastroCozinha.salvar(cozinha2);

        cadastroCozinha
            .listar()
            .forEach(cozinha -> System.out.println(cozinha.getNome()));

        var cozinhaARemover = cadastroCozinha.buscar(3L);
        cadastroCozinha.remover(cozinhaARemover);

        cadastroCozinha
            .listar()
            .forEach(cozinha -> System.out.println(cozinha.getNome()));
    }
}
