package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name = "CoberturaTransportadora")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CoberturaTransportadoraId.class)
public class CoberturaTransportadoraModel implements Serializable {

    @Id
    @Column(name = "idTransportadora")
    private Integer idTransportadora;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoEnum estado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTransportadora", insertable = false, updatable = false)
    private TransportadoraModel  transportadora;
}
