package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.transportadora.TransportadoraDetalhesDTO;
import com.projeto.projetofarmaciatcsframework.DTO.transportadora.TransportadoraRegistroDTO;
import com.projeto.projetofarmaciatcsframework.models.CoberturaTransportadoraId;
import com.projeto.projetofarmaciatcsframework.models.CoberturaTransportadoraModel;
import com.projeto.projetofarmaciatcsframework.models.TransportadoraModel;
import com.projeto.projetofarmaciatcsframework.models.EstadoEnum;
import com.projeto.projetofarmaciatcsframework.repository.CoberturaTransportadoraRepository;
import com.projeto.projetofarmaciatcsframework.repository.TransportadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

                CoberturaTransportadoraId coberturaId = new CoberturaTransportadoraId();
                coberturaId.setTransportadoraId(transportadoraSalva.getIdTransportadora());
                coberturaId.setEstado(estadoEnum);

                CoberturaTransportadoraModel cobertura = new CoberturaTransportadoraModel();
                cobertura.setId(coberturaId);
                cobertura.setTransportadora(transportadoraSalva);
                cobertura.setEstado(estadoEnum);

                coberturaRepository.save(cobertura);

            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Estado inválido fornecido: " + siglaEstado);
            }
        }
    }

    public List<TransportadoraDetalhesDTO> listarTodasComCobertura() {
        List<TransportadoraModel> transportadoras = transportadoraRepository.findAll();

        return transportadoras.stream()
                .map(transportadora -> {
                    List<String> siglasEstados = transportadora.getCoberturas().stream()
                            .map(cobertura -> cobertura.getEstado().name())
                            .toList();

                    return new TransportadoraDetalhesDTO(
                            transportadora.getIdTransportadora(),
                            transportadora.getNome(),
                            siglasEstados
                    );
                })
                .toList();
    }

    public void excluirTransportadora(Integer id) {
        if (!transportadoraRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Nenhuma transportadora encontrada com o ID: " + id, 1);
        }
        transportadoraRepository.deleteById(id);
    }
}
