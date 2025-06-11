package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.repository.NegociosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NegociosService {

    @Autowired
    private NegociosRepository negociosRepository;
}
