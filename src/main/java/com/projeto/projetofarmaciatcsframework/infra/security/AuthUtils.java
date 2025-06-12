package com.projeto.projetofarmaciatcsframework.infra.security;

import com.projeto.projetofarmaciatcsframework.models.UsuarioModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

public class AuthUtils {

    public static Integer getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UsuarioModel user) {
            return user.getId(); // Retorna o ID do usuário logado
        }
        return null;
    }
}
