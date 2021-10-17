package com.jordan.algafoods.jpa;

import com.jordan.algafoods.AlgafoodsApplication;
import com.jordan.algafoods.domain.model.Cozinha;
import com.jordan.algafoods.domain.repository.CozinhaRepository;
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

        var cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        cozinhaRepository.salvar(cozinha1);
        cozinhaRepository.salvar(cozinha2);

        System.out.println(cozinhaRepository
            .buscar(1L).getNome());

        System.out.println(cozinhaRepository
            .buscar(2L).getNome());

        System.out.println(cozinhaRepository
            .buscar(3L).getNome());

        System.out.println(cozinhaRepository
            .buscar(4L).getNome());

    }
}
