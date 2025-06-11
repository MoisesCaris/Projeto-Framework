package com.projeto.projetofarmaciatcsframework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoberturaTransportadoraId implements Serializable {

    private Integer idTransportadora;
    private EstadoEnum estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoberturaTransportadoraId that = (CoberturaTransportadoraId) o;
        return Objects.equals(idTransportadora, that.idTransportadora) &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransportadora, estado);
    }
}
