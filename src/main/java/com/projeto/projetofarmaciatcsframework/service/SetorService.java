package com.projeto.projetofarmaciatcsframework.service;

import com.projeto.projetofarmaciatcsframework.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;
}
