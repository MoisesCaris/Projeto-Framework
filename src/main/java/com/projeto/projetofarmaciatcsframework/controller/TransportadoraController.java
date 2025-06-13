package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.transportadora.TransportadoraDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.DTO.transportadora.TransportadoraRegistroDTO;
import com.projeto.projetofarmaciatcsframework.service.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    private TransportadoraService transportadoraService;

    @GetMapping
    public ResponseEntity<List<TransportadoraDetalhesDTO>> listarTodas() {
        List<TransportadoraDetalhesDTO> transportadoras = transportadoraService.listarTodasComCobertura();
        return ResponseEntity.ok(transportadoras);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        transportadoraService.excluirTransportadora(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registro")
    public ResponseEntity<Void> registrar(@RequestBody TransportadoraRegistroDTO dto) {
        transportadoraService.registrarNovaTransportadora(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
