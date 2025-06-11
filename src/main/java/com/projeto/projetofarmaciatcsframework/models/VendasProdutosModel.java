package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "VendaProdutos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendasProdutosModel {

    @ManyToOne
    @JoinColumn(name = "idVenda")
    private VendaModel venda;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private ProdutosModel produtos;

    @Column(name = "qtdCompraProduto", nullable = false)
    private int qtdCompra;

    @Column(name = "valorCompraProduto", nullable = false)
    private double valorCompra;
}
