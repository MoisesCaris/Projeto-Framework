package com.projeto.projetofarmaciatcsframework.mappers;
import com.projeto.projetofarmaciatcsframework.models.VendaModel;
import com.projeto.projetofarmaciatcsframework.DTO.produtos.RegistroProdutoDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.FuncionarioModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    @Mapping(target = "idVenda", ignore = true)
    @Mapping(target = "idVendaProduto", ignore = true)
    @Mapping(target = "idFuncionario", source = "funcionarioModel")
    @Mapping(target = "farmacia", source = "farmaciaModel")
    VendaModel registrarVenda(RegistroVendaDTO data, FarmaciaModel farmaciaModel, FuncionarioModel funcionarioModel);
}
