package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.auth.AuthenticationDTO;
import com.projeto.projetofarmaciatcsframework.DTO.auth.RegisterDTO;
import com.projeto.projetofarmaciatcsframework.DTO.funcionario.FuncionarioAtualizarDTO;
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
    public void registro2Funcionario(RegisterDTO data, Integer userID, FarmaciaModel farmaciaModel) {
        GeneroEnum generoEnum = GeneroEnum.valueOf(data.genero().toString());
        FuncionarioModel funcionarioModel = mapper.adicionarFuncionario2(data,userID, generoEnum, farmaciaModel);
        this.funcionarioRepository.save(funcionarioModel);
    }

    public List<FuncionarioDetalhesDTO> listarTodos(Integer farmaciaID) {
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).get();
        return funcionarioRepository.findByFarmacia(farmaciaModel);
    }

    public void excluirFuncionario(Integer id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Nenhum funcionário encontrado com o ID: " + id, 1);
        }
        funcionarioRepository.deleteById(id);
    }

    public FuncionarioDetalhesDTO listarFuncionario(Integer id) {
        return funcionarioRepository.findByIdFuncionario(id);
    }

    public void atualizarFuncionario(Integer id, FuncionarioAtualizarDTO data, Integer farmaciaID) {
        GeneroEnum generoEnum = GeneroEnum.valueOf(data.genero().toString());
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).get();
        SetorModel setorModel = setorRepository.findById(data.setorID()).get();
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(id).get();
        funcionarioModel = mapper.atualizarFuncionario(data, funcionarioModel, generoEnum, id, farmaciaModel , setorModel);
        funcionarioRepository.save(funcionarioModel);
    }
}
