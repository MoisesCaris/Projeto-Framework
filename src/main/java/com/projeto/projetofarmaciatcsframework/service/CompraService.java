package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
}
