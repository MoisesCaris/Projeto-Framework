package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.compra.CompraProdutoDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.mappers.CompraMapper;
import com.projeto.projetofarmaciatcsframework.models.*;
import com.projeto.projetofarmaciatcsframework.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FarmaciaRepository farmaciaRepository;
    @Autowired
    private CompraMapper mapper;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private ComprasProdutoRepository comprasProdutoRepository;

    public void registrarVenda(VendaRegistroDTO data, Integer userId, Integer farmaciaID) {
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(userId).orElseThrow();
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).orElseThrow();
        CompraModel compraModel = mapper.registroCompra(data,farmaciaModel, funcionarioModel);
        this.compraRepository.save(compraModel);
    }

    public void registrarProdutos(CompraProdutoDTO data){
        CompraModel compraModel = compraRepository.findById(data.idCompra()).orElseThrow();
        ProdutosModel produtoModel = produtosRepository.findById(data.idProduto()).orElseThrow();
        ComprasProdutoModel comprasProdutoModel = mapper.registroProduto(data,compraModel,produtoModel);
        this.comprasProdutoRepository.save(comprasProdutoModel);
    }
}
