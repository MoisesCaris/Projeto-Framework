package com.projeto.projetofarmaciatcsframework.DTO.venda;

public record VendaListagemDTO(
        int idVenda,
        String nomeFuncionario,
        java.util.Date dataVenda,
        double totalCompra
) {}
