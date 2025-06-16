package com.projeto.projetofarmaciatcsframework.mappers;

import com.projeto.projetofarmaciatcsframework.DTO.compra.CompraProdutoDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompraMapper {

    @Mapping( target = "idCompra", ignore = true)
    @Mapping(target = "funcionario", source = "funcionarioModel")
    @Mapping(target = "totalCompra", source = "data.totalVenda")
    @Mapping(target = "dataCompra", source = "data.dataVenda")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    CompraModel registroCompra(VendaRegistroDTO data, FarmaciaModel farmaciaModel, FuncionarioModel funcionarioModel);

    @Mapping(target = "idcompra")
    ComprasProdutoModel registroProduto(CompraProdutoDTO data, CompraModel compraModel, ProdutosModel produtosModel);
}
