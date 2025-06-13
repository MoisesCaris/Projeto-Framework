package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.funcionario.RegistroFuncionarioDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/adicionar")
    public ResponseEntity adicionarFuncionario(@RequestBody RegistroFuncionarioDTO data) {
        Integer userId = AuthUtils.getCurrentUserId();
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        funcionarioService.registrarFuncionario(data, userId, farmaciaID) ;
        return ResponseEntity.ok().build();
    }
}
