package com.projeto.projetofarmaciatcsframework.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "compraProdutos")
@Table(name = "CompraProdutos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CompraProdutosId.class)
public class ComprasProdutoModel implements Serializable {

    @Column(name = "qtdcompraproduto", nullable = false)
    @Min(value = 1, message = "Quantidade deve ser maior que 0")
    private Integer qtdCompraProduto;

    @Column(name = "valorcompraproduto", nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Valor deve ser maior ou igual a 0")
    private BigDecimal valorCompraProduto;

    @ManyToOne
    @Id
    @JoinColumn(name = "idcompra", insertable = false, updatable = false)
    private CompraModel compra;

    @ManyToOne
    @Id
    @JoinColumn(name = "idproduto", insertable = false, updatable = false)
    private ProdutosModel produto;
}
