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
    @JoinColumn(name = "idfuncionario")
    private FuncionarioModel funcionario;

    @Column(name = "datacompra", nullable = false)
    private LocalDate dataCompra;

    @Column(name = "totalcompra", nullable = false)
    private double totalCompra;

    @ManyToOne
    @JoinColumn(name = "idfarmacia")
    private FarmaciaModel farmacia;
}
