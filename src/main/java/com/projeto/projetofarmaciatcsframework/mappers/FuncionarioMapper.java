package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.models.FuncionarioModel;
import com.projeto.projetofarmaciatcsframework.DTO.funcionario.RegistroFuncionarioDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.SetorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    @Mapping(target = "idFuncionario", ignore = true)
    @Mapping(target = "nomeCompleto", source = "data.nomeFuncionario")
    @Mapping(target = "idade", source = "data.idade")
    @Mapping(target = "genero", source = "data.genero")
    @Mapping(target = "salarioBase", source = "data.salarioBase")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    @Mapping(target = "setor", source = "setorModel")
    FuncionarioModel adicionarFuncionario(RegistroFuncionarioDTO data, FarmaciaModel farmaciaModel, SetorModel setorModel);


    
}
