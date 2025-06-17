package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.setor.RegistroSetorDTO;
import com.projeto.projetofarmaciatcsframework.DTO.setor.SetorDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.projeto.projetofarmaciatcsframework.infra.security.AuthUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setores")
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
    @GetMapping("/listar")
    public ResponseEntity<List<SetorDetalhesDTO>> listarSetores() {
        Integer farmaciaID = AuthUtils.getCurrentUserFarmaciaId();
        List<SetorDetalhesDTO> setores = setorService.listarTodos(farmaciaID);
        return ResponseEntity.ok(setores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        setorService.excluirSetor(id);
        return ResponseEntity.noContent().build();
    }
}
