package com.projeto.projetofarmaciatcsframework.DTO.funcionario;

import com.projeto.projetofarmaciatcsframework.models.GeneroEnum;

public record FuncionarioDetalhesDTO(
        Integer id,
        String nomeCompleto,
        int idade,
        GeneroEnum genero,
        String nomeSetor,
        String nomeFarmacia
) {}
