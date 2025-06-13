package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.transportadora.TransportadoraRegistroDTO;
import com.projeto.projetofarmaciatcsframework.service.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    private TransportadoraService transportadoraService;

    @PostMapping("/registro")
    public ResponseEntity<Void> registrar(@RequestBody TransportadoraRegistroDTO dto) {
        transportadoraService.registrarNovaTransportadora(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
