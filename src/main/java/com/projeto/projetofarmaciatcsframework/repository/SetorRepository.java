package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.DTO.setor.SetorDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.SetorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetorRepository extends JpaRepository<SetorModel, Integer> {

    @Query("select new com.projeto.projetofarmaciatcsframework.DTO.setor.SetorDetalhesDTO(s.idSetor, s.nome, s.valeAlimentacao, s.valeRefeicao, s.valeTransporte, s.planoOdonto, s.planoSaude) "+
    "from setor s "+
    "where s.farmacia = :farmaciaModel")
    List<SetorDetalhesDTO> toDTO(FarmaciaModel farmaciaModel);
}
