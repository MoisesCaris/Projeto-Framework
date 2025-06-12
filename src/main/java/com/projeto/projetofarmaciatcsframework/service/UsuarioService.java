package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.UsuarioModel;
import com.projeto.projetofarmaciatcsframework.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioModel getUsuario(Integer id){
        return usuarioRepository.getById(id);
    }

    public Object setID(int id, FarmaciaModel farmaciaModel){
        UsuarioModel usuario = getUsuario(id);
        usuario.setFarmaciaID(farmaciaModel);
        return usuarioRepository.save(usuario);
    }
}
