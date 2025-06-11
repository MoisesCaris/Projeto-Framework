package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Farmacia")
@Entity(name = "farmacia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmaciaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFarmacia;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;
}
