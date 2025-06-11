package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;
}
