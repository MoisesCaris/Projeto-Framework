package com.projeto.projetofarmaciatcsframework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutosId implements Serializable {

    private Integer idVenda;
    private Integer idProduto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendaProdutosId that = (VendaProdutosId) o;
        return Objects.equals(idVenda, that.idVenda) &&
                Objects.equals(idProduto, that.idProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenda, idProduto);
    }
}