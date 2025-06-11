package com.projeto.projetofarmaciatcsframework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraProdutosId implements Serializable {

    private Integer idCompra;
    private Integer idProduto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraProdutosId that = (CompraProdutosId) o;
        return Objects.equals(idCompra, that.idCompra) &&
                Objects.equals(idProduto, that.idProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, idProduto);
    }
}
