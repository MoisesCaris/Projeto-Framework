package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.setor.RegistroSetorDTO;
import com.projeto.projetofarmaciatcsframework.DTO.setor.SetorDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.mappers.SetorMapper;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.SetorModel;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<SetorDetalhesDTO> listarTodos() {
        return setorRepository.findAll()
                .stream()
                .map(setor -> new SetorDetalhesDTO(
                        setor.getIdSetor(),
                        setor.getNome(),
                        setor.getValeAlimentacao(),
                        setor.getValeRefeicao(),
                        setor.getValeTransporte(),
                        setor.getPlanoOdonto(),
                        setor.getPlanoSaude()
                ))
                .collect(Collectors.toList());
    }

    public void excluirSetor(Integer id) {
        if (!setorRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Nenhum setor encontrado com o ID: " + id, 1);
        }
        setorRepository.deleteById(id);
    }
}
