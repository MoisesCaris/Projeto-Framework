package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.mappers.CompraMapper;
import com.projeto.projetofarmaciatcsframework.models.CompraModel;
import com.projeto.projetofarmaciatcsframework.models.FarmaciaModel;
import com.projeto.projetofarmaciatcsframework.models.FuncionarioModel;
import com.projeto.projetofarmaciatcsframework.models.VendaModel;
import com.projeto.projetofarmaciatcsframework.repository.CompraRepository;
import com.projeto.projetofarmaciatcsframework.repository.ComprasProdutoRepository;
import com.projeto.projetofarmaciatcsframework.repository.FarmaciaRepository;
import com.projeto.projetofarmaciatcsframework.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FarmaciaRepository farmaciaRepository;
    @Autowired
    private CompraMapper mapper;
    @Autowired
    private ComprasProdutoRepository comprasProdutoRepository;

    public void registrarVenda(VendaRegistroDTO data, Integer userId, Integer farmaciaID) {
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(userId).orElseThrow();
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).orElseThrow();
        CompraModel compraModel = mapper.registroCompra(data,farmaciaModel, funcionarioModel);
        this.compraRepository.save(compraModel);
    }

    public void registrarProdutos(){

    }
}
