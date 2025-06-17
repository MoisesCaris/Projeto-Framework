package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaCaixaDTO;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FarmaciaRepository extends JpaRepository<FarmaciaModel, Integer> {

    @Query("""
    SELECT new com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaCaixaDTO(
        f.nome,
        f.cnpj,
        (f.caixaInicial
         + COALESCE((SELECT SUM(v.totalVenda) FROM venda v WHERE v.farmacia = f), 0)
         - COALESCE((SELECT SUM(c.totalCompra) FROM compra c WHERE c.farmacia = f), 0)
        )
    )
    FROM farmacia f
    WHERE f.idFarmacia = :farmaciaId
""")
    FarmaciaCaixaDTO findSaldo(Integer farmaciaId);
}
