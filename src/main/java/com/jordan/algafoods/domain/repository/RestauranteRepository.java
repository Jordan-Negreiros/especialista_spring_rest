package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long id);
    Optional<Restaurante> findFirstByNomeContaining(String nome);
    List<Restaurante> findTop2ByNomeContaining(String nome);
    boolean existsByNome(String nome);
    int countByCozinhaId(Long cozinhaId);
}
