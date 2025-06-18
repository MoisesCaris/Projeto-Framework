package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.DTO.funcionario.FuncionarioDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {

    @Query("select new com.projeto.projetofarmaciatcsframework.DTO.funcionario.FuncionarioDetalhesDTO(f.idFuncionario,f.nomeCompleto, f.idade, f.salarioBase, f.genero, s.nome, fa.nome) "+
    "from funcionario f "+
    "join f.farmacia fa "+
    "join f.setor s "+
    "where f.farmacia = :farmacia ")
    List<FuncionarioDetalhesDTO> findByFarmacia(FarmaciaModel farmacia);

    @Query("select new com.projeto.projetofarmaciatcsframework.DTO.funcionario.FuncionarioDetalhesDTO(f.idFuncionario,f.nomeCompleto, f.idade, f.salarioBase, f.genero, s.nome, fa.nome) "+
            "from funcionario f "+
            "join f.farmacia fa "+
            "join f.setor s "+
            "where f.idFuncionario = :id")
    FuncionarioDetalhesDTO findByIdFuncionario(Integer id);
}
