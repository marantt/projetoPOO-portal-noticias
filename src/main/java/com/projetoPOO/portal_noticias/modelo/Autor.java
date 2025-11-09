package com.projetoPOO.portal_noticias.modelo;

public class Autor {
    
    // Atributos do autor
    private String nome;
    private String email;

    // Construtor
    public Autor(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
