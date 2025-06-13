package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.FuncionarioModel;
import com.projeto.projetofarmaciatcsframework.models.VendaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    @Mapping( target = "idVenda", ignore = true)
    @Mapping(target = "funcionario", source = "funcionarioModel")
    @Mapping(target = "totalVenda", source = "data.totalVenda")
    @Mapping(target = "dataVenda", source = "data.dataVenda")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    VendaModel registroVenda(VendaRegistroDTO data, FarmaciaModel farmaciaModel, FuncionarioModel funcionarioModel);

}
