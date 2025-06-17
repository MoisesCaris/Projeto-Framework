package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.DTO.compra.CompraListagemDTO;
import com.projeto.projetofarmaciatcsframework.DTO.compra.CompraProdutoDTO;
import com.projeto.projetofarmaciatcsframework.DTO.venda.VendaRegistroDTO;
import com.projeto.projetofarmaciatcsframework.mappers.CompraMapper;
import com.projeto.projetofarmaciatcsframework.models.*;
import com.projeto.projetofarmaciatcsframework.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private ProdutosRepository produtosRepository;

    @Autowired
    private ComprasProdutoRepository comprasProdutoRepository;

    @Transactional
    public int registrarVenda(VendaRegistroDTO data, Integer userId, Integer farmaciaID) {
        FuncionarioModel funcionarioModel = funcionarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).orElseThrow(() -> new RuntimeException("Farmácia não encontrada."));
        CompraModel compraModel = mapper.registroCompra(data, farmaciaModel, funcionarioModel);
        this.compraRepository.save(compraModel);
        return compraModel.getIdCompra();
    }

    @Transactional
    public void registrarProdutos(CompraProdutoDTO data) {
        CompraModel compraModel = compraRepository.findById(data.idCompra())
                .orElseThrow(() -> new RuntimeException("Compra com ID " + data.idCompra() + " não encontrada."));
        ProdutosModel produtoModel = produtosRepository.findById(data.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto com ID " + data.idProduto() + " não encontrado."));

        produtoModel.setQuantidade(produtoModel.getQuantidade() + data.qtdCompraProduto());
        produtosRepository.save(produtoModel);

        double valorDoItem = produtoModel.getValorCusto() * data.qtdCompraProduto();
        compraModel.setTotalCompra(compraModel.getTotalCompra() + valorDoItem);
        compraRepository.save(compraModel);

        Optional<ComprasProdutoModel> itemExistenteOpt = comprasProdutoRepository.findByCompra_IdCompraAndProduto_IdProduto(data.idCompra(), data.idProduto());

        if (itemExistenteOpt.isPresent()) {
            ComprasProdutoModel itemExistente = itemExistenteOpt.get();
            int novaQuantidade = itemExistente.getQtdCompraProduto() + data.qtdCompraProduto();
            itemExistente.setQtdCompraProduto(novaQuantidade);
            comprasProdutoRepository.save(itemExistente);
        } else {
            ComprasProdutoModel novoItem = mapper.registroProduto(data, compraModel, produtoModel);
            this.comprasProdutoRepository.save(novoItem);
        }
    }

    public List<CompraListagemDTO> listarCompras(Integer farmaciaID) {
        FarmaciaModel farmaciaModel = farmaciaRepository.findById(farmaciaID).get();
        return compraRepository.toDTO(farmaciaModel);
    }
}
