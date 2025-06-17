package com.projeto.projetofarmaciatcsframework.controller;

import com.projeto.projetofarmaciatcsframework.DTO.auth.AuthenticationDTO;
import com.projeto.projetofarmaciatcsframework.DTO.auth.LoginResponseDTO;
import com.projeto.projetofarmaciatcsframework.DTO.auth.RegisterDTO;
import com.projeto.projetofarmaciatcsframework.infra.security.TokenService;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.UsuarioModel;
import com.projeto.projetofarmaciatcsframework.repository.UsuarioRepository;
import com.projeto.projetofarmaciatcsframework.service.FarmaciaService;
import com.projeto.projetofarmaciatcsframework.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FarmaciaService  farmaciaService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {
        var userPassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.senha());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((UsuarioModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.findByLogin(registerDTO.login()) == null) {
            var encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.senha());
            var user = new UsuarioModel(registerDTO.login(), encryptedPassword, registerDTO.acesso());
            this.userRepository.save(user);
            FarmaciaModel farmaciaModel = farmaciaService.registrarFarmacia2(registerDTO, user.getId());
            funcionarioService.registro2Funcionario(registerDTO, user.getId(), farmaciaModel);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/")
    public ResponseEntity getToken() {
        return ResponseEntity.accepted().body("Teste");
    }
    @PostMapping("/")
    public ResponseEntity f() {
        return ResponseEntity.accepted().body("Teste");
    }
}