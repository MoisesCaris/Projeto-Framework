package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaListagemDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<VendaModel, Integer> {

    @Query("select new com.projeto.projetofarmaciatcsframework.DTO.venda.VendaListagemDTO(v.idVenda, f.nomeCompleto, v.dataVenda,v.totalVenda) "+
    "from venda v "+
    "join funcionario f "+
    "where v.farmacia = :farmaciaModel")
    List<VendaListagemDTO> toDTO(FarmaciaModel farmaciaModel);
}
