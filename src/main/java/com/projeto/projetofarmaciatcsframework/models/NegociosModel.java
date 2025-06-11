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
    @JoinColumn(name = "idCompra")
    private CompraModel compra;

    @ManyToOne
    @JoinColumn(name = "idVenda")
    private VendaModel venda;

    @ManyToOne
    @JoinColumn(name = "idFarmacia")
    private FarmaciaModel farmacia;
}
