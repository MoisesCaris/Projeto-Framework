package com.projeto.projetofarmaciatcsframework.DTO.auth;

import com.projeto.projetofarmaciatcsframework.models.GeneroEnum;
import com.projeto.projetofarmaciatcsframework.models.UsuarioAcesso;

public record RegisterDTO(String login, String senha, UsuarioAcesso acesso, String nomeFuncionario, double salarioBase, GeneroEnum genero, int idade, String nome, String cnpj) {
}
