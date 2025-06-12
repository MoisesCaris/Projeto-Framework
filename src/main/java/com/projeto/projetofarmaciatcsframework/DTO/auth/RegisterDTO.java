package com.projeto.projetofarmaciatcsframework.DTO.auth;

import com.projeto.projetofarmaciatcsframework.models.UsuarioAcesso;

public record RegisterDTO(String login, String senha, UsuarioAcesso acesso) {
}
