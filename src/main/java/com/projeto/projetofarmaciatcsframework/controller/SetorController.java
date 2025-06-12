package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.RegistroSetorDTO;
import com.projeto.projetofarmaciatcsframework.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/set")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @PostMapping("/registrar")
    public ResponseEntity registrarSetor(@RequestBody RegistroSetorDTO data){
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        setorService.registroSetor(data, farmaciaID);
        System.out.println("Registrar Setor");
        return ResponseEntity.status(HttpStatus.CREATED).body("Setor Criado com Sucesso");
    }
}
