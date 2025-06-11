package com.projeto.projetofarmaciatcsframework.DTO;

import com.projeto.projetofarmaciatcsframework.models.UsuarioAcesso;

public record RegisterDTO(String login, String senha, UsuarioAcesso acesso) {
}
