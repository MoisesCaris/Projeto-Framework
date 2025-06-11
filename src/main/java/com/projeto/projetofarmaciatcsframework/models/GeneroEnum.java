package com.projeto.projetofarmaciatcsframework.models;


public enum GeneroEnum {Masculino("Masculino"), Feminino("Feminino"), Outros("Outros");

    private String genero;

    GeneroEnum(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
