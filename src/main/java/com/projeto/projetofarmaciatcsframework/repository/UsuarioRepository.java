package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    UserDetails findByLogin(String login);
}
