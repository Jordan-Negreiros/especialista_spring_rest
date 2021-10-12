package com.jordan.algafoods.jpa;

import com.jordan.algafoods.AlgafoodsApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodsApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        applicationContext
            .getBean(CadastroCozinha.class)
            .listar()
            .forEach(cozinha -> System.out.println(cozinha.getNome()));
    }
}
