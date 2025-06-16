package com.projeto.projetofarmaciatcsframework.DTO.setor;

public record SetorDetalhesDTO(
        Integer id,
        String nome,
        double valeAlimentacao,
        double valeRefeicao,
        double valeTransporte,
        double planoOdonto,
        double planoSaude
) {}
