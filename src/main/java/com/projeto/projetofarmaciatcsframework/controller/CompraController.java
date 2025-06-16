package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.compra.CompraProdutoDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.CaixaService;
import com.projeto.projetofarmaciatcsframework.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("/comprar")
    public ResponseEntity criarCompra(@RequestBody VendaRegistroDTO data) {
        Integer userId = AuthUtils.getCurrentUserId();
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        return ResponseEntity.status(HttpStatus.CREATED).body(compraService.registrarVenda(data,userId,farmaciaID));
    }

    @PostMapping("/adicionar")
    public ResponseEntity registrarProdutos(@RequestBody CompraProdutoDTO data) {
        compraService.registrarProdutos(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
