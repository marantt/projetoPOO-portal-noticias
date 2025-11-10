package com.projetoPOO.portal_noticias.modelo;

public class NoticiaComVideo extends Noticia{

    // Atributo específico para a notícia com vídeo
    private String urlVideo;

    // Construtor
    public NoticiaComVideo(String titulo, String conteudo, Autor autor, String urlVideo) {
        super(titulo, conteudo, autor);
        this.urlVideo = urlVideo;
    }

    @Override
    public String getTipoNoticia() {
        return "Vídeo";
    }

    public String getUrlVideo() {
        return urlVideo;
    }

}
