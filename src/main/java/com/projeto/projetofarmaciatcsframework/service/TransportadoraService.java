package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.transportadora.TransportadoraRegistroDTO;
import com.projeto.projetofarmaciatcsframework.models.CoberturaTransportadoraId;
import com.projeto.projetofarmaciatcsframework.models.CoberturaTransportadoraModel;
import com.projeto.projetofarmaciatcsframework.models.TransportadoraModel;
import com.projeto.projetofarmaciatcsframework.models.EstadoEnum;
import com.projeto.projetofarmaciatcsframework.repository.CoberturaTransportadoraRepository;
import com.projeto.projetofarmaciatcsframework.repository.TransportadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    @Autowired
    private CoberturaTransportadoraRepository coberturaRepository;

    @Transactional
    public void registrarNovaTransportadora(TransportadoraRegistroDTO dto) {
        TransportadoraModel novaTransportadora = new TransportadoraModel();
        novaTransportadora.setNome(dto.nome());
        TransportadoraModel transportadoraSalva = transportadoraRepository.save(novaTransportadora);

        for (String siglaEstado : dto.estadosAtendidos()) {
            try {
                EstadoEnum estadoEnum = EstadoEnum.valueOf(siglaEstado.toUpperCase());

                // *** INÍCIO DA CORREÇÃO ***
                // a. Cria a chave primária composta
                CoberturaTransportadoraId coberturaId = new CoberturaTransportadoraId();
                coberturaId.setTransportadoraId(transportadoraSalva.getIdTransportadora()); // Usa o ID da transportadora salva
                coberturaId.setEstado(estadoEnum);

                // b. Cria a entidade de cobertura
                CoberturaTransportadoraModel cobertura = new CoberturaTransportadoraModel();
                cobertura.setId(coberturaId); // Define a chave composta que acabamos de criar
                cobertura.setTransportadora(transportadoraSalva); // Define a relação
                cobertura.setEstado(estadoEnum); // Define o campo mapeado

                // c. Salva a entidade de cobertura no banco
                coberturaRepository.save(cobertura);
                // *** FIM DA CORREÇÃO ***

            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Estado inválido fornecido: " + siglaEstado);
            }
        }
    }
}
