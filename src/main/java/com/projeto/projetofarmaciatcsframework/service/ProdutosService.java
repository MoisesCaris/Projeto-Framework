package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.produtos.ProdutoDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.DTO.produtos.RegistroProdutoDTO;
import com.projeto.projetofarmaciatcsframework.mappers.ProdutosMapper;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.ProdutosModel;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProdutoDetalhesDTO> listarTodos() {
        // 1. Busca todas as entidades do banco
        List<ProdutosModel> produtos = produtosRepository.findAll();

        // 2. Converte (mapeia) cada entidade para o seu DTO correspondente
        return produtos.stream()
                .map(produto -> new ProdutoDetalhesDTO(
                        produto.getIdProduto(),
                        produto.getNomeProduto(),
                        produto.getValorVenda(),
                        produto.getValorCusto(),
                        produto.getQuantidade()
                ))
                .collect(Collectors.toList()); // Coleta os resultados em uma lista
    }
}
