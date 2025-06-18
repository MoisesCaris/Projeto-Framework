package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.auth.RegisterDTO;
import com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaCaixaDTO;
import com.projeto.projetofarmaciatcsframework.DTO.farmacia.FarmaciaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.DTO.farmacia.LucroAnualDTO;
import com.projeto.projetofarmaciatcsframework.DTO.farmacia.LucroMensalDTO;
import com.projeto.projetofarmaciatcsframework.mappers.FarmaciaMapper;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FarmaciaMapper mapper;


    public void registrarFarmacia(FarmaciaRegistroDTO data, Integer userId) {
        FarmaciaModel farmaciaModel = mapper.registroFarmacia(data);
        this.farmaciaRepository.save(farmaciaModel);
        usuarioService.setID(userId, farmaciaModel);
    }

    public FarmaciaModel registrarFarmacia2(RegisterDTO data, Integer userId) {
        FarmaciaModel farmaciaModel = mapper.registroFarmacia2(data);
        this.farmaciaRepository.save(farmaciaModel);
        usuarioService.setID(userId, farmaciaModel);
        return farmaciaModel;
    }

    public FarmaciaCaixaDTO calcularCaixa(Integer farmaciaId) {
        return farmaciaRepository.findSaldo(farmaciaId);
    }

    public List<LucroMensalDTO> calcularLucroMensal(Integer farmaciaId) {
        Map<String, Double> mapaLucro = new HashMap<>();
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaId).get();
        for (LucroMensalDTO v : farmaciaRepository.obterVendasMensais(farmaciaModel)) {
            String chave = v.ano() + "-" + v.mes();
            mapaLucro.put(chave, v.lucro());
        }

        for (LucroMensalDTO c : farmaciaRepository.obterComprasMensais(farmaciaModel)) {
            String chave = c.ano() + "-" + c.mes();
            mapaLucro.put(chave, mapaLucro.getOrDefault(chave, 0.0) + c.lucro());
        }

        return mapaLucro.entrySet().stream()
                .map(entry -> {
                    String[] partes = entry.getKey().split("-");
                    return new LucroMensalDTO(
                            Integer.parseInt(partes[0]),
                            Integer.parseInt(partes[1]),
                            entry.getValue()
                    );
                })
                .sorted(Comparator.comparing(LucroMensalDTO::ano)
                        .thenComparing(LucroMensalDTO::mes))
                .toList();
    }

    public List<LucroAnualDTO> calcularLucroAnual(Integer farmaciaId) {
        Map<Integer, Double> mapaLucro = new HashMap<>();
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaId).get();
        for (LucroAnualDTO v : farmaciaRepository.obterVendasAnuais(farmaciaModel)) {
            mapaLucro.put(v.ano(), v.lucro());
        }

        for (LucroAnualDTO c : farmaciaRepository.obterComprasAnuais(farmaciaModel)) {
            mapaLucro.put(c.ano(), mapaLucro.getOrDefault(c.ano(), 0.0) + c.lucro());
        }

        return mapaLucro.entrySet().stream()
                .map(entry -> new LucroAnualDTO(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(LucroAnualDTO::ano))
                .toList();
    }
}
