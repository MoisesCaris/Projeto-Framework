package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Produto")
@Entity(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduto")
    private int idProduto;

    @Column(name = "nomeproduto", nullable = false)
    private String nomeProduto;

    @Column(name = "valorvenda", nullable = false)
    private double valorVenda;

    @Column(name = "valorcusto", nullable = false)
    private double valorCusto;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "idfarmacia")
    private FarmaciaModel farmacia;
}
