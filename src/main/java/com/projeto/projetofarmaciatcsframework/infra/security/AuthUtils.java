package com.projeto.projetofarmaciatcsframework.infra.security;

import com.projeto.projetofarmaciatcsframework.models.UsuarioModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

public class AuthUtils {

    public static UsuarioModel getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UsuarioModel user) {
            return user;
        }
        return null;
    }

    public static Integer getCurrentUserId() {
        var user = getCurrentUser();
        return user != null ? user.getId() : null;
    }

    public static Integer getCurrentUserFarmaciaId() {
        UsuarioModel usuario = getCurrentUser();
        return (usuario != null && usuario.getFarmaciaID() != null)
                ? usuario.getFarmaciaID().getIdFarmacia()
                : null;
    }
}
