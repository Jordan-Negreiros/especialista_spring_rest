package com.jordan.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// @JsonRootName("cozinha") -> altera identificador no XML
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // @JsonProperty("nomeCozinha") -> altera a representação do json para "nomeCozinha"
    // @JsonIgnore -> retira o campo da representação
    @Column(nullable = false)
    private String nome;

}
