package com.projeto.projetofarmaciatcsframework.DTO.produtos;

public record ProdutoDetalhesDTO(
        Integer id,
        String nomeProduto,
        double valorVenda,
        double valorCusto,
        int quantidade
) {}
