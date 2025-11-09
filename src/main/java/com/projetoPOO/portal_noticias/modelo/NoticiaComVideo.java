package com.projetoPOO.portal_noticias.modelo;

public class NoticiaComVideo extends Noticia{

    // Atributo específico para a notícia com vídeo
    private String urlVideo;

    // Construtor
    public NoticiaComVideo(String titulo, String conteudo, Autor autor, String urlVideo) {
        super(titulo, conteudo, autor);
        this.urlVideo = urlVideo;
    }

    // Método para gerar a exibição da notícia ***** criei so pra exemplo  ****
    @Override
    public String exibirNoticia() {
        StringBuilder html = new StringBuilder();
        html.append("<h1>").append(getTitulo()).append("</h1>");
        html.append("<p>").append(getConteudo()).append("</p>");
        html.append("<p><strong>Autor:</strong> ").append(getAutor().getNome()).append("</p>");
        html.append("<p><strong>Vídeo:</strong> <a href=\"").append(urlVideo).append("\">Assistir</a></p>");
        return html.toString();
    }

    public String getUrlVideo() {
        return urlVideo;
    }

}
