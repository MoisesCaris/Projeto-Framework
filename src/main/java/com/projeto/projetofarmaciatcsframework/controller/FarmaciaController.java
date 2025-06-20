package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmacia")
public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;


    @PostMapping("/registrar")
    public ResponseEntity registrarFarmacia(@RequestBody FarmaciaRegistroDTO data){
        Integer userId = AuthUtils.getCurrentUserId();
        farmaciaService.registrarFarmacia(data, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Farmacia registrado com sucesso");
    }

    @GetMapping("/caixa")
    public ResponseEntity caixa(){
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        return ResponseEntity.ok(farmaciaService.calcularCaixa(farmaciaID));
    }

    @GetMapping("/mensal")
    public ResponseEntity mensal(){
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        return ResponseEntity.ok(farmaciaService.calcularLucroMensal(farmaciaID));
    }

    @GetMapping("/anual")
    public ResponseEntity anual(){
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        return ResponseEntity.ok(farmaciaService.calcularLucroAnual(farmaciaID));
    }
}
