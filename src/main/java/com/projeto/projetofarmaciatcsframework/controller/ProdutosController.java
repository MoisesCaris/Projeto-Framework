package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.produtos.RegistroProdutoDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
