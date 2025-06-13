package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CoberturaTransportadoraId implements Serializable {

    private int transportadoraId;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;
}
