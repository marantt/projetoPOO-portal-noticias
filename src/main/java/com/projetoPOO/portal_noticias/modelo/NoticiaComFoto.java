package com.projetoPOO.portal_noticias.modelo;


public class NoticiaComFoto extends Noticia{

    private String urlFoto;

    // Construtor
    public NoticiaComFoto(String titulo, String conteudo, Autor autor, String urlFoto) {
        super(titulo, conteudo, autor);
        this.urlFoto = urlFoto;
    }

    @Override
    public String getTipoNoticia() {
        return "Foto";
    }

    // Getters
    public String getUrlFoto() {
        return urlFoto;
    }

    // Setters
    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    

}
