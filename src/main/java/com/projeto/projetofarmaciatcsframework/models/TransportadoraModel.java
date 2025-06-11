package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Transportadora")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportadoraModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTransportadora")
    private Integer idTransportadora;

    @Column(name = "nome", nullable = false, length = 150)
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    private String nome;

    @OneToMany(mappedBy = "transportadora", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoberturaTransportadoraModel> coberturas;
}