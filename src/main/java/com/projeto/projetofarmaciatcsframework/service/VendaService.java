package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.mappers.VendaMapper;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.FuncionarioModel;
import com.projeto.projetofarmaciatcsframework.models.VendaModel;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.FuncionarioRepository;
import com.projeto.projetofarmaciatcsframework.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private VendaMapper mapper;

    public void registrarVenda(VendaRegistroDTO data,Integer userId,Integer farmaciaID) {
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(userId).orElseThrow();
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).orElseThrow();
        VendaModel vendaModel = mapper.registroVenda(data,farmaciaModel, funcionarioModel);
        this.vendaRepository.save(vendaModel);
    }
}
