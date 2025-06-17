package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaListagemDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaProdutoDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.mappers.VendaMapper;
import com.projeto.projetofarmaciatcsframework.models.*;
import com.projeto.projetofarmaciatcsframework.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private VendaMapper mapper;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private VendaProdutoRepository vendaProdutoRepository;

    @Transactional
    public int registrarVenda(VendaRegistroDTO data, Integer userId, Integer farmaciaID) {
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).orElseThrow(() -> new RuntimeException("Farmácia não encontrada."));
        VendaModel vendaModel = mapper.registroVenda(data, farmaciaModel, funcionarioModel);
        this.vendaRepository.save(vendaModel);
        return vendaModel.getIdVenda();
    }

    @Transactional
    public void registrarProdutos(VendaProdutoDTO data) {
        VendaModel vendaModel = vendaRepository.findById(data.idVenda())
                .orElseThrow(() -> new RuntimeException("Venda com ID " + data.idVenda() + " não encontrada."));
        ProdutosModel produtoModel = produtosRepository.findById(data.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto com ID " + data.idProduto() + " não encontrado."));

        if (produtoModel.getQuantidade() < data.qtdVendaProduto()) {
            throw new RuntimeException("Estoque insuficiente para o produto com ID " + data.idProduto());
        }

        produtoModel.setQuantidade(produtoModel.getQuantidade() - data.qtdVendaProduto());
        produtosRepository.save(produtoModel);

        double valorDoItem = produtoModel.getValorCusto() * data.qtdVendaProduto();
        vendaModel.setTotalVenda(vendaModel.getTotalVenda() + valorDoItem);
        vendaRepository.save(vendaModel);

        Optional<VendaProdutosModel> itemExistenteOpt = vendaProdutoRepository.findByVenda_IdVendaAndProduto_IdProduto(data.idVenda(), data.idProduto());

        if (itemExistenteOpt.isPresent()) {
            VendaProdutosModel itemExistente = itemExistenteOpt.get();
            int novaQuantidade = itemExistente.getQtdVendaProduto() + data.qtdVendaProduto();
            itemExistente.setQtdVendaProduto(novaQuantidade);
            vendaProdutoRepository.save(itemExistente);
        } else {
            VendaProdutosModel novoItem = mapper.registroProduto(data, vendaModel, produtoModel);
            this.vendaProdutoRepository.save(novoItem);
        }
    }

    public List<VendaListagemDTO> listarVendas() {
        return vendaRepository.findAll().stream().map(venda ->
                new VendaListagemDTO(
                        venda.getIdVenda(),
                        venda.getFuncionario().getNomeCompleto(),
                        venda.getDataVenda(),
                        venda.getTotalVenda()
                )
        ).collect(Collectors.toList());
    }
}
