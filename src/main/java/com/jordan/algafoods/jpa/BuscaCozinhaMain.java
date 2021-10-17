package com.jordan.algafoods.jpa;

import com.jordan.algafoods.AlgafoodsApplication;
import com.jordan.algafoods.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodsApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        var cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");

        var cozinha2 = new Cozinha();
        cozinha2.setNome("Japonesa");

        var cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        cadastroCozinha.adicionar(cozinha1);
        cadastroCozinha.adicionar(cozinha2);

        System.out.println(cadastroCozinha
            .buscar(1L).getNome());

        System.out.println(cadastroCozinha
            .buscar(2L).getNome());

        System.out.println(cadastroCozinha
            .buscar(3L).getNome());

        System.out.println(cadastroCozinha
            .buscar(4L).getNome());

    }
}
