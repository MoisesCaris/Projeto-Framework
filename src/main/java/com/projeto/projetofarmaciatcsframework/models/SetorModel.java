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
    @Column(name = "idsetor")
    private int idSetor;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "valerefeicao")
    private double valeRefeicao;

    @Column(name = "valealimentacao")
    private double valeAlimentacao;

    @Column(name = "planosaude")
    private double planoSaude;

    @Column(name = "planoodonto")
    private double planoOdonto;

    @Column(name = "valetransporte")
    private double valeTransporte;

    @ManyToOne
    @JoinColumn(name = "idfarmacia")
    private FarmaciaModel farmacia;
}
