package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.produtos.ProdutoDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.DTO.produtos.RegistroProdutoDTO;
import com.projeto.projetofarmaciatcsframework.mappers.ProdutosMapper;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.ProdutosModel;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public List<ProdutoDetalhesDTO> listarTodos(Integer farmaciaID) {
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).get();
        return produtosRepository.toDTO(farmaciaModel);
    }

    public void excluirProduto(Integer id) {
        if (!produtosRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Nenhum produto encontrado com o ID: " + id, 1);
        }
        produtosRepository.deleteById(id);
    }
}
