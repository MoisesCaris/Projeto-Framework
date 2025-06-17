package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.produtos.ProdutoDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.DTO.produtos.RegistroProdutoDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @PostMapping("/registro")
    public ResponseEntity registrar(@RequestBody RegistroProdutoDTO data){
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        produtosService.registrarProduto(data, farmaciaID);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDetalhesDTO>> listar() {
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        List<ProdutoDetalhesDTO> listaDeProdutos = produtosService.listarTodos(farmaciaID);
        return ResponseEntity.ok(listaDeProdutos);
    }
}
