package com.projeto.projetofarmaciatcsframework.DTO.funcionario;

import com.projeto.projetofarmaciatcsframework.models.GeneroEnum;

public record RegistroFuncionarioDTO(String nomeFuncionario, double salarioBase, GeneroEnum genero, int idade) {
}
