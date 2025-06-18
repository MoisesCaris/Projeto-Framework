package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaCaixaDTO;
import com.projeto.projetofarmaciatcsframework.DTO.farmacia.LucroAnualDTO;
import com.projeto.projetofarmaciatcsframework.DTO.farmacia.LucroMensalDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmaciaRepository extends JpaRepository<FarmaciaModel, Integer> {

    @Query("""
    SELECT new com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaCaixaDTO(
        f.nome,
        f.cnpj,
        (
            f.caixaInicial
            + COALESCE((
                SELECT SUM(v.totalVenda)
                FROM venda v
                WHERE v.farmacia = f
                  AND v.dataVenda <= CURRENT_DATE
            ), 0)
            - COALESCE((
                SELECT SUM(c.totalCompra)
                FROM compra c
                WHERE c.farmacia = f
                  AND c.dataCompra <= CURRENT_DATE
            ), 0)
        )
    )
    FROM farmacia f
    WHERE f.idFarmacia = :farmaciaId
""")
    FarmaciaCaixaDTO findSaldo(@Param("farmaciaId") Integer farmaciaId);

    @Query("""
    SELECT new com.projeto.projetofarmaciatcsframework.DTO.farmacia.LucroMensalDTO(
        YEAR(v.dataVenda),
        MONTH(v.dataVenda),
        SUM(v.totalVenda)
    )
    FROM venda v
    WHERE v.farmacia = :farmaciaId
    GROUP BY YEAR(v.dataVenda), MONTH(v.dataVenda)
""")
    List<LucroMensalDTO> obterVendasMensais(FarmaciaModel farmaciaId);

    @Query("""
    SELECT new com.projeto.projetofarmaciatcsframework.DTO.farmacia.LucroMensalDTO(
        YEAR(c.dataCompra),
        MONTH(c.dataCompra),
        -SUM(c.totalCompra)
    )
    FROM compra c
    WHERE c.farmacia = :farmaciaId
    GROUP BY YEAR(c.dataCompra), MONTH(c.dataCompra)
""")
    List<LucroMensalDTO> obterComprasMensais(FarmaciaModel farmaciaId);

    @Query("""
    SELECT new com.projeto.projetofarmaciatcsframework.DTO.farmacia.LucroAnualDTO(
        YEAR(v.dataVenda),
        SUM(v.totalVenda)
    )
    FROM venda v
    WHERE v.farmacia = :farmaciaId
    GROUP BY YEAR(v.dataVenda)
""")
    List<LucroAnualDTO> obterVendasAnuais(FarmaciaModel farmaciaId);

    @Query("""
    SELECT new com.projeto.projetofarmaciatcsframework.DTO.farmacia.LucroAnualDTO(
        YEAR(c.dataCompra),
        -SUM(c.totalCompra)
    )
    FROM compra c
    WHERE c.farmacia = :farmaciaId
    GROUP BY YEAR(c.dataCompra)
""")
    List<LucroAnualDTO> obterComprasAnuais(FarmaciaModel farmaciaId);
}
