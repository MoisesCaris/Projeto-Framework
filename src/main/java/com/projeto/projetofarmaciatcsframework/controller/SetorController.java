package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setor")
public class SetorController {

    @Autowired
    private SetorService setorService;

//    @PostMapping("/registro")
//    public ResponseEntity registrarSetor(@RequestBody RegistroSetorDTO data){
//        return new ResponseEntity.status(HttpStatus.CREATED).body("Setor Criado com Sucesso");
//    }
}
