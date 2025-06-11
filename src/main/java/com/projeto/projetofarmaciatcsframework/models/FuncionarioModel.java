package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Funcionario")
@Entity(name = "funcionario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFuncionario;

    @Column(name = "nomeCompleto", nullable = false)
    private String nomeCompleto;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Column(name = "genero", nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @ManyToOne
    @JoinColumn(name = "idSetor")
    private SetorModel setor;

    @Column(name = "salarioBase", nullable = false)
    private double salarioBase;

    @ManyToOne
    @JoinColumn(name = "idFarmacia")
    private FarmaciaModel farmacia;
}
