package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.setor.RegistroSetorDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.SetorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SetorMapper {

    @Mapping(target = "idSetor", ignore = true)
    @Mapping(target = "nome", source = "data.nome")
    @Mapping(target = "valeRefeicao", ignore = true)
    @Mapping(target = "valeAlimentacao", ignore = true)
    @Mapping(target = "planoOdonto", ignore = true)
    @Mapping(target = "valeTransporte", ignore = true)
    @Mapping(target = "farmacia", source = "farmaciaModel")
    SetorModel registroSetor(RegistroSetorDTO data, FarmaciaModel farmaciaModel);
}
