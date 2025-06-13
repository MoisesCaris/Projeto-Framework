package com.projeto.projetofarmaciatcsframework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "NegociosEmAndamento")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegociosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNegocio;

    @ManyToOne
    @JoinColumn(name = "idcompra")
    private CompraModel compra;

    @ManyToOne
    @JoinColumn(name = "idvenda")
    private VendaModel venda;

    @ManyToOne
    @JoinColumn(name = "idfarmacia")
    private FarmaciaModel farmacia;
}
