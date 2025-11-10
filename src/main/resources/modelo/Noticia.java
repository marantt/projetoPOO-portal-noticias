package com.projetoPOO.portal_noticias.modelo;

import java.time.LocalDateTime;

public abstract class Noticia {

    // Atributos comuns a todas as notícias
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
    public abstract String exibirNoticia();

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

}
