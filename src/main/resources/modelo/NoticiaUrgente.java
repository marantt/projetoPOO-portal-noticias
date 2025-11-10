package com.projetoPOO.portal_noticias.modelo;

public class NoticiaUrgente extends Noticia{
    
    // Construtor
    public NoticiaUrgente(String titulo, String conteudo, Autor autor) {
        super(titulo, conteudo, autor);
    }
    
    // Método para gerar a exibição da notícia urgente
    @Override
    public String exibirNoticia() {
        return "URGENTE: " + getTitulo() + "\n" +
               getConteudo() + "\n" +
               "Publicado em: " + getDataPublicacao() + "\n" +
               "Autor: " + getAutor().getNome() + " (" + getAutor().getEmail() + ")";
    }

}
