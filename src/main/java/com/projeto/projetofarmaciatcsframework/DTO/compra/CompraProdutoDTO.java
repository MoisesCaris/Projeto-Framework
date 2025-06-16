package com.projeto.projetofarmaciatcsframework.DTO.compra;

import java.math.BigDecimal;

public record CompraProdutoDTO(Integer idCompra, Integer idProduto, Integer qtdCompraProduto, BigDecimal valorCompraProduto) {
}
