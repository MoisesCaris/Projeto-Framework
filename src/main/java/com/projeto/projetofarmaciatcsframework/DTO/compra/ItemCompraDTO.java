package com.projeto.projetofarmaciatcsframework.DTO.compra;

public record ItemCompraDTO(
        String nomeProduto,
        int quantidadeComprada,
        double valorUnitario // O preço do produto no momento da compra
) {}
