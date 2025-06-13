package com.projeto.projetofarmaciatcsframework.DTO.transportadora;

import java.util.List;

public record TransportadoraDetalhesDTO(Integer id, String nome, List<String> estadosCobertura) {
}
