package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.FarmaciaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
