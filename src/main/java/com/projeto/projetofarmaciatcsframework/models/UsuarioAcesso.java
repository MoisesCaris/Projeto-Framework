package com.projeto.projetofarmaciatcsframework.models;

public enum UsuarioAcesso {
    GERENTE("gerente"),
    FUNCIONARIO("funcionario");

    private String role;

    UsuarioAcesso(String role) {
        this.role = role;
    }

    public String getAcesso() {
        return role;
    }
}
