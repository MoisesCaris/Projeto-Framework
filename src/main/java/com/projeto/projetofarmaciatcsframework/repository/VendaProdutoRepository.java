package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.models.VendaProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendaProdutoRepository extends JpaRepository<VendaProdutosModel, Integer> {
    Optional<VendaProdutosModel> findByVenda_IdVendaAndProduto_IdProduto(Integer idVenda, Integer idProduto);
}
