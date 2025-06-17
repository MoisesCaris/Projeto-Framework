package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.auth.RegisterDTO;
import com.projeto.projetofarmaciatcsframework.models.FuncionarioModel;
import com.projeto.projetofarmaciatcsframework.DTO.funcionario.RegistroFuncionarioDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.GeneroEnum;
import com.projeto.projetofarmaciatcsframework.models.SetorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    @Mapping(target = "idFuncionario", source = "userId")
    @Mapping(target = "nomeCompleto", source = "data.nomeFuncionario")
    @Mapping(target = "idade", source = "data.idade")
    @Mapping(target = "genero", source = "generoEnum")
    @Mapping(target = "salarioBase", source = "data.salarioBase")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    @Mapping(target = "setor", source = "setorModel")
    FuncionarioModel adicionarFuncionario(RegistroFuncionarioDTO data, FarmaciaModel farmaciaModel, SetorModel setorModel, Integer userId,GeneroEnum generoEnum);

    @Mapping(target = "idFuncionario", source = "userId")
    @Mapping(target = "nomeCompleto", source = "data.nomeFuncionario")
    @Mapping(target = "idade", source = "data.idade")
    @Mapping(target = "genero", source = "generoEnum")
    @Mapping(target = "salarioBase", source = "data.salarioBase")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    @Mapping(target = "setor", ignore = true)
    FuncionarioModel adicionarFuncionario2(RegisterDTO data, Integer userId, GeneroEnum generoEnum, FarmaciaModel farmaciaModel);
}
