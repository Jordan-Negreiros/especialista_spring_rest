package com.jordan.algafoods.domain.repository;

import com.jordan.algafoods.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
