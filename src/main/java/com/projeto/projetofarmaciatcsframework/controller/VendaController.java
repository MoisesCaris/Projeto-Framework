package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.compra.CompraListagemDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaListagemDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaProdutoDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/vender")
    public ResponseEntity criarVenda(@RequestBody VendaRegistroDTO data) {
        Integer userId = AuthUtils.getCurrentUserId();
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.registrarVenda(data,userId,farmaciaID));
    }

    @PostMapping("/adicionar")
    public ResponseEntity registrarProdutos(@RequestBody VendaProdutoDTO data) {
        vendaService.registrarProdutos(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<VendaListagemDTO>> listarVendas() {
        return ResponseEntity.ok(vendaService.listarVendas());
    }
}
