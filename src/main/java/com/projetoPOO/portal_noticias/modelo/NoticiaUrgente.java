package com.projetoPOO.portal_noticias.modelo;

public class NoticiaUrgente extends Noticia{
    
    // Construtor
    public NoticiaUrgente(String titulo, String conteudo, Autor autor) {
        super(titulo, conteudo, autor);
    }

    @Override
    public String getTipoNoticia() {
        return "Urgente";
    }

}
