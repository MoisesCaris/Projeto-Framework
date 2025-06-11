package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Setor")
@Entity(name = "setor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSetor;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "valeRefeicao")
    private double valeRefeicao;

    @Column(name = "valeAlimentacao")
    private double valeAlimentacao;

    @Column(name = "planoSaude")
    private double planoSaude;

    @Column(name = "planoOdonto")
    private double planoOdonto;

    @Column(name = "valeTransporte")
    private double valeTransporte;

    @ManyToOne
    @JoinColumn(name = "idFarmacia")
    private FarmaciaModel farmacia;
}
