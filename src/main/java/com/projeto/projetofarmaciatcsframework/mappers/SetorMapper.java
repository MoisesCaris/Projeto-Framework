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
    @Mapping(target = "valeRefeicao", source = "data.valeRefeicao")
    @Mapping(target = "valeAlimentacao", source = "data.valeAlimentacao")
    @Mapping(target = "planoOdonto", source = "data.planoOdonto")
    @Mapping(target = "valeTransporte", source = "data.valeTransporte")
    @Mapping(target = "planoSaude", source = "data.planoSaude")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    SetorModel registroSetor(RegistroSetorDTO data, FarmaciaModel farmaciaModel);
}
