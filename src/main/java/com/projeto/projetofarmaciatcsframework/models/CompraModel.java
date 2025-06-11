package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "Compra")
@Entity(name = "compra")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompra;

    @ManyToOne
    @JoinColumn(name = "idFuncionario")
    private FuncionarioModel funcionario;

    @Column(name = "dataCompra", nullable = false)
    private LocalDate dataCompra;

    @Column(name = "totalCompra", nullable = false)
    private double totalCompra;

    @ManyToOne
    @JoinColumn(name = "idFarmacia")
    private FarmaciaModel farmacia;
}
