package com.projeto.projetofarmaciatcsframework.DTO.funcionario;

public record FuncionarioDetalhesDTO(
        Integer id,
        String nomeCompleto,
        int idade,
        String genero,
        String nomeSetor,
        String nomeFarmacia
) {}
