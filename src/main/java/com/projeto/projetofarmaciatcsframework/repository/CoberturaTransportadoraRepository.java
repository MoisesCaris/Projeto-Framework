package com.projeto.projetofarmaciatcsframework.repository;

import com.projeto.projetofarmaciatcsframework.models.CoberturaTransportadoraModel;
import com.projeto.projetofarmaciatcsframework.models.CoberturaTransportadoraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoberturaTransportadoraRepository extends JpaRepository<CoberturaTransportadoraModel, CoberturaTransportadoraId> {
}
