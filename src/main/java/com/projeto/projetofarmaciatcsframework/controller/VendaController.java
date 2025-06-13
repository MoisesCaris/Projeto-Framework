package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/vender")
    public ResponseEntity registrarVenda(@RequestBody VendaRegistroDTO data) {
        Integer userId = AuthUtils.getCurrentUserId();
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        vendaService.registrarVenda(data,userId,farmaciaID);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
