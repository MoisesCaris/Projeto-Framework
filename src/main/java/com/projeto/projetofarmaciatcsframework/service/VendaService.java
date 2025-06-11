package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
}
