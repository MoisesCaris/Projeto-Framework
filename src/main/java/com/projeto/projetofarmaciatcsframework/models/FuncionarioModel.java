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
    @Column(name = "idfuncionario")
    private int idFuncionario;

    @Column(name = "nomecompleto", nullable = false)
    private String nomeCompleto;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Column(name = "genero", nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @ManyToOne
    @JoinColumn(name = "idsetor")
    private SetorModel setor;

    @Column(name = "salariobase", nullable = false)
    private double salarioBase;

    @ManyToOne
    @JoinColumn(name = "idfarmacia")
    private FarmaciaModel farmacia;
}
