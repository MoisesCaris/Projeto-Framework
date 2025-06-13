package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coberturatransportadora")
@Getter
@Setter
public class CoberturaTransportadoraModel {

    @EmbeddedId
    private CoberturaTransportadoraId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("transportadoraId")
    @JoinColumn(name = "idtransportadora")
    private TransportadoraModel transportadora;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", insertable = false, updatable = false)
    private EstadoEnum estado;
}
