package com.projetoPOO.portal_noticias.modelo;

public class Autor {
    
    // Atributos do autor
    private int id;
    private String nome;
    private String email;

    // Form
    public Autor() {
    }

    // Select
    public Autor(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Insert
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

    public int getId() {
        return id;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

}
