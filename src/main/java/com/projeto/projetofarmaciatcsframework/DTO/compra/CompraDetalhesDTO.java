package com.projeto.projetofarmaciatcsframework.DTO.compra;

import java.time.LocalDate;
import java.util.List;


public record CompraDetalhesDTO(
        Integer idCompra,
        LocalDate dataCompra,
        Double totalCompra,
        String nomeFuncionario,
        List<ItemCompraDTO> itens
) {}
