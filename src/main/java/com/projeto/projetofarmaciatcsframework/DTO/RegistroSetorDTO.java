package com.projeto.projetofarmaciatcsframework.DTO;

import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;

public record RegistroSetorDTO(String nome, double valeRefeicao, double valeAlimentacao, double planoOdonto, double valeTransporte, int id) {
}
