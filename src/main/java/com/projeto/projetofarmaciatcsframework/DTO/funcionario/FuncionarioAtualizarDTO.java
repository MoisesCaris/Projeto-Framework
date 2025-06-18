package com.projeto.projetofarmaciatcsframework.DTO.funcionario;

import com.projeto.projetofarmaciatcsframework.models.GeneroEnum;

public record FuncionarioAtualizarDTO(String nomeFuncionario, double salarioBase, GeneroEnum genero, int idade, Integer setorID) {
}
