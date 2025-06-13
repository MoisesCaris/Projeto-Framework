package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.VendaProdutosModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendaProdutosMapper {

    @Mapping(target = "idProduto", ignore = true)
    @Mapping(target = "qtdProduto", source = "data.qtdProduto")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    VendaProdutosModel vendaProdutos(VendaRegistroDTO data, FarmaciaModel farmaciaModel);
    
}
