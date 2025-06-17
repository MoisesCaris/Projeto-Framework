package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.DTO.produtos.ProdutoDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<ProdutosModel, Integer> {

    @Query("select new com.projeto.projetofarmaciatcsframework.DTO.produtos.ProdutoDetalhesDTO(p.idProduto ,p.nomeProduto, p.valorVenda, p.valorCusto, p.quantidade) "+
    "from produto p "+
    "where p.farmacia = :farmaciaModel")
    List<ProdutoDetalhesDTO> toDTO(FarmaciaModel farmaciaModel);
}
