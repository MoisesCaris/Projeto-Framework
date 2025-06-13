package com.projeto.projetofarmaciatcsframework.DTO.funcionario;

import com.projeto.projetofarmaciatcsframework.models.GeneroEnum;
import com.projeto.projetofarmaciatcsframework.models.UsuarioAcesso;

public record RegistroFuncionarioDTO(String login, UsuarioAcesso usuarioAcesso, String nomeFuncionario, double salarioBase, GeneroEnum genero, int idade, Integer setorID) {
}
