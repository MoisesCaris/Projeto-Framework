package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.produtos.RegistroProdutoDTO;
import com.projeto.projetofarmaciatcsframework.mappers.ProdutosMapper;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.ProdutosModel;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private ProdutosMapper  mapper;

    public void registrarProduto(RegistroProdutoDTO data, int farmaciaID){
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).get();
        ProdutosModel produtos = mapper.criarProduto(data, farmaciaModel);
        this.produtosRepository.save(produtos);
    }
}
