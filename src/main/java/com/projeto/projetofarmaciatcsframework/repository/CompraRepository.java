package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.DTO.compra.CompraListagemDTO;
import com.projeto.projetofarmaciatcsframework.models.CompraModel;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompraRepository extends JpaRepository<CompraModel, Integer> {

    @Query("select new com.projeto.projetofarmaciatcsframework.DTO.compra.CompraListagemDTO(c.idCompra, f.nomeCompleto, c.dataCompra, c.totalCompra) "+
    "from compra c "+
    "join funcionario f "+
    "where c.farmacia = :farmaciaModel")
    List<CompraListagemDTO> toDTO(FarmaciaModel farmaciaModel);
}
