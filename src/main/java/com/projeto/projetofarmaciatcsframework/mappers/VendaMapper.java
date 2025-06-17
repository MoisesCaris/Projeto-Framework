package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaProdutoDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.models.*;
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

    @Mapping(target = "idVenda", source = "data.idVenda")
    @Mapping(target = "idProduto", source = "data.idProduto")
    @Mapping(target = "venda", source = "vendaModel")
    @Mapping(target = "produto", source = "produtosModel")
    @Mapping(target = "qtdVendaProduto", source = "data.qtdVendaProduto")
    @Mapping(target = "valorVendaProduto", ignore = true)
    VendaProdutosModel registroProduto(VendaProdutoDTO data, VendaModel vendaModel, ProdutosModel produtosModel);
}
