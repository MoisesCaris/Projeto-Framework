package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.caixa.RegistroCaixaDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.CaixaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CaixaMapper {

    @Mapping(target = "idCaixa", ignore = true)
    @Mapping(target = "valor", source = "data.valor")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    CaixaModel registroCaixa(RegistroCaixaDTO data, FarmaciaModel farmaciaModel);
    
}
