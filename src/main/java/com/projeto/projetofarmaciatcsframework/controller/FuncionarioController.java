package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.funcionario.FuncionarioAtualizarDTO;
import com.projeto.projetofarmaciatcsframework.DTO.funcionario.FuncionarioDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.DTO.funcionario.RegistroFuncionarioDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import com.projeto.projetofarmaciatcsframework.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<FuncionarioDetalhesDTO>> listarFuncionarios() {
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        List<FuncionarioDetalhesDTO> funcionarios = funcionarioService.listarTodos(farmaciaID);
        return ResponseEntity.ok(funcionarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        funcionarioService.excluirFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDetalhesDTO> buscarFuncionarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(funcionarioService.listarFuncionario(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody FuncionarioAtualizarDTO data) {
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        funcionarioService.atualizarFuncionario(id, data, farmaciaID);
        return ResponseEntity.ok("Funcionario atualizado com sucesso!");
    }
}