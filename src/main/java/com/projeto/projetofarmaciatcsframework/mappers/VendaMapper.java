package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.produtos.RegistroProdutoDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.ProdutosModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    @Mapping(target = "idProduto", ignore = true)
    @Mapping(target = "nomeProduto", source = "data.nomeProduto")
    @Mapping(target = "valorVenda", source = "data.valorVenda")
    @Mapping(target = "valorCusto", source = "data.valorCusto")
    @Mapping(target = "quantidade", ignore = true)
    @Mapping(target = "farmacia", source = "farmaciaModel")
    ProdutosModel criarProduto(RegistroProdutoDTO data, FarmaciaModel farmaciaModel);

}
