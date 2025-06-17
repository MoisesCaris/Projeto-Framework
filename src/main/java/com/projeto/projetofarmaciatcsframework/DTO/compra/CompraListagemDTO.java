package com.projeto.projetofarmaciatcsframework.DTO.compra;

public record CompraListagemDTO(
        int idCompra,
        String nomeFuncionario,
        java.time.LocalDate dataCompra,
        double totalCompra
) {}
