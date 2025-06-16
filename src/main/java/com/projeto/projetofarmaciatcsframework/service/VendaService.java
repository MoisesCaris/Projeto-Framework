package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.compra.CompraProdutoDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.mappers.VendaMapper;
import com.projeto.projetofarmaciatcsframework.models.*;
import com.projeto.projetofarmaciatcsframework.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private VendaMapper mapper;

    @Autowired
    private VendaProdutosRepository vendaProdutosRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    public void registrarVenda(VendaRegistroDTO data,Integer userId,Integer farmaciaID) {
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(userId).orElseThrow();
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).orElseThrow();
        VendaModel vendaModel = mapper.registroVenda(data,farmaciaModel, funcionarioModel);
        this.vendaRepository.save(vendaModel);
    }

    public void registroProduto(CompraProdutoDTO data){
        VendaModel vendaModel = vendaRepository.findById(data.idCompra()).orElseThrow();
        ProdutosModel produtoModel = produtosRepository.findById(data.idProduto()).orElseThrow();
        VendaProdutosModel  vendaProdutosModel = mapper.registroProduto(data,vendaModel,produtoModel);
        this.vendaProdutosRepository.save(vendaProdutosModel);
    }
}
