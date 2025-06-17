package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.auth.RegisterDTO;
import com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.mappers.FarmaciaMapper;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FarmaciaMapper mapper;


    public void registrarFarmacia(FarmaciaRegistroDTO data, Integer userId) {
        FarmaciaModel farmaciaModel = mapper.registroFarmacia(data);
        this.farmaciaRepository.save(farmaciaModel);
        usuarioService.setID(userId, farmaciaModel);
    }

    public FarmaciaModel registrarFarmacia2(RegisterDTO data, Integer userId) {
        FarmaciaModel farmaciaModel = mapper.registroFarmacia2(data);
        this.farmaciaRepository.save(farmaciaModel);
        usuarioService.setID(userId, farmaciaModel);
        return farmaciaModel;
    }
}
