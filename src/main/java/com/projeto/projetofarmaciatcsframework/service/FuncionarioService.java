package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.funcionario.FuncionarioDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.DTO.funcionario.RegistroFuncionarioDTO;
import com.projeto.projetofarmaciatcsframework.mappers.FuncionarioMapper;
import com.projeto.projetofarmaciatcsframework.models.*;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.FuncionarioRepository;
import com.projeto.projetofarmaciatcsframework.repository.SetorRepository;
import com.projeto.projetofarmaciatcsframework.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private FuncionarioMapper mapper;

    @Autowired
    private UsuarioRepository userRepository;

    public void registrarFuncionario(RegistroFuncionarioDTO data, Integer userId,Integer farmaciaID) {
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).get();
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.login());
        var user = new UsuarioModel(data.login(), encryptedPassword, data.usuarioAcesso());
        if (userRepository.findByLogin(data.login()) == null) {
            user.setFarmaciaID(farmaciaModel);
            this.userRepository.save(user);
        }else{
        }
        GeneroEnum generoEnum = GeneroEnum.valueOf(data.genero().toString());
        SetorModel setorModel = setorRepository.findById(data.setorID()).get();
        FuncionarioModel funcionarioModel = mapper.adicionarFuncionario(data,farmaciaModel,setorModel,user.getId(), generoEnum);
        this.funcionarioRepository.save(funcionarioModel);
    }

    public List<FuncionarioDetalhesDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(this::toFuncionarioDetalhesDTO)
                .collect(Collectors.toList());
    }

    private FuncionarioDetalhesDTO toFuncionarioDetalhesDTO(FuncionarioModel funcionario) {
        return new FuncionarioDetalhesDTO(
                funcionario.getIdFuncionario(),
                funcionario.getNomeCompleto(),
                funcionario.getIdade(),
                funcionario.getGenero() != null ? funcionario.getGenero().toString() : "Não informado",
                funcionario.getSetor() != null ? funcionario.getSetor().getNome() : "Não definido",
                funcionario.getFarmacia() != null ? funcionario.getFarmacia().getNome() : "Não definida"
        );
    }

    public void excluirFuncionario(Integer id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Nenhum funcionário encontrado com o ID: " + id, 1);
        }
        funcionarioRepository.deleteById(id);
    }
}
