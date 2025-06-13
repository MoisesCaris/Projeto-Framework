package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "Venda")
@Entity(name = "venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvenda")
    private int idVenda;

    @ManyToOne
    @JoinColumn(name = "idfuncionario")
    private FuncionarioModel funcionario;

    @Column(name = "datavenda", nullable = false)
    private LocalDate dataVenda;

    @Column(name = "totalvenda", nullable = true)
    private double totalVenda;

    @ManyToOne
    @JoinColumn(name = "idfarmacia")
    private FarmaciaModel farmacia;
}
