package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.RegistroSetorDTO;
import com.projeto.projetofarmaciatcsframework.mappers.SetorMapper;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.SetorModel;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private SetorMapper mapper;

    public void registroSetor(RegistroSetorDTO data, Integer farmaciaID){
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).get();
        SetorModel setorModel =  mapper.registroSetor(data, farmaciaModel);
        this.setorRepository.save(setorModel);
    }
}
