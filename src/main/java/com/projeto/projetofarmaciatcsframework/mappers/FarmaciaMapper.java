package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.auth.RegisterDTO;
import com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmaciaMapper {

    @Mapping(target = "idFarmacia", ignore = true)
    @Mapping(target = "nome", source = "data.nome")
    @Mapping(target = "cnpj", source = "data.cnpj")
    FarmaciaModel registroFarmacia(FarmaciaRegistroDTO data);

    @Mapping(target = "idFarmacia", ignore = true)
    @Mapping(target = "nome", source = "data.nome")
    @Mapping(target = "cnpj", source = "data.cnpj")
    FarmaciaModel registroFarmacia2(RegisterDTO data);
}
