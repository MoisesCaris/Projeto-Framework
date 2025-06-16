package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.models.ComprasProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComprasProdutoRepository extends JpaRepository<ComprasProdutoModel, Integer> {
    Optional<ComprasProdutoModel> findByCompra_IdCompraAndProduto_IdProduto(Integer idCompra, Integer idProduto);
}
