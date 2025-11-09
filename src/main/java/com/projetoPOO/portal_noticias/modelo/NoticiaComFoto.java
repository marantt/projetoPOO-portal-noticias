package com.projetoPOO.portal_noticias.modelo;

import java.util.List;

public class NoticiaComFoto extends Noticia{

    private List<String> urlsFotos;

    // Construtor
    public NoticiaComFoto(String titulo, String conteudo, Autor autor, List<String> urlsFotos) {
        super(titulo, conteudo, autor);
        this.urlsFotos = urlsFotos;
    }

    // Método para gerar a exibição da notícia ***** criei so pra exemplo  ****
    @Override
    public String exibirNoticia() {
        StringBuilder html = new StringBuilder();
        html.append("<h1>").append(getTitulo()).append("</h1>");
        html.append("<p>").append(getConteudo()).append("</p>");
        html.append("<p><strong>Autor:</strong> ").append(getAutor().getNome()).append("</p>");
        html.append("<p><strong>Foto:</strong> <img src=\"").append(urlsFotos).append("\" alt=\"Foto da notícia\"></p>");
        return html.toString();
    }

    // Getter
    public List<String> getUrlsFotos() {
        return urlsFotos;
    }

}
