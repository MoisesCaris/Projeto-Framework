package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "VendaProdutos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(VendaProdutosId.class)
public class VendaProdutosModel implements Serializable {

    @Id
    @Column(name = "idVenda")
    private Integer idVenda;

    @Id
    @Column(name = "idProduto")
    private Integer idProduto;

    @Column(name = "qtdVendaProduto", nullable = false)
    @Min(value = 1, message = "Quantidade deve ser maior que 0")
    private Integer qtdVendaProduto;

    @Column(name = "valorVendaProduto", nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Valor deve ser maior ou igual a 0")
    private BigDecimal valorVendaProduto;

    @ManyToOne
    @JoinColumn(name = "idVenda", insertable = false, updatable = false)
    private VendaModel venda;

    @ManyToOne
    @JoinColumn(name = "idProduto", insertable = false, updatable = false)
    private ProdutosModel produto;
}
