package com.projetoPOO.portal_noticias.modelo;

import java.time.LocalDateTime;

public abstract class Noticia {

    // Atributos 
    private int id;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private Autor autor;

    // Construtor
    public Noticia(String titulo, String conteudo, Autor autor){
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDateTime.now();
        this.autor = autor;
    }

    // Método abstrato para gerar a exibição da notícia
    public abstract String getTipoNoticia();

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public int getId() {
        return id;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    

}
