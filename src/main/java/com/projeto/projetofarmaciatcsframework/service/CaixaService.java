package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.caixa.RegistroCaixaDTO;
import com.projeto.projetofarmaciatcsframework.mappers.CaixaMapper;
import com.projeto.projetofarmaciatcsframework.models.CaixaModel;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.repository.CaixaRepository;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private CaixaMapper caixaMapper;

    public CaixaModel registroCaixa(RegistroCaixaDTO dto, int idFarmacia) {
       
        FarmaciaModel farmacia = farmaciaRepository.findById(idFarmacia).get();
        CaixaModel caixa = caixaMapper.registroCaixa(dto, farmacia);

        return this.caixaRepository.save(caixa);
    }
}