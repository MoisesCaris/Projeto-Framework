package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Caixa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaixaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCaixa;

    @Column(name = "valor", nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "idFarmacia")
    private FarmaciaModel farmacia;
}
