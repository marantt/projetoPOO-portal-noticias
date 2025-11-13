package com.projetoPOO.portal_noticias.modelo;

public class NoticiaTextoSimples extends Noticia {

    // Atributo específico para NoticiaTextoSimples
    private String fonteMateria;

    // Construtor
    public NoticiaTextoSimples(String titulo, String conteudo, Autor autor, String fonteMateria) {
        super(titulo, conteudo, autor);
        this.fonteMateria = fonteMateria;
    }

    @Override
    public String getTipoNoticia() {    
        return "Texto";
    }

    // Getters
    public String getFonteMateria() {
        return fonteMateria;
    }

    // Setters
    public void setFonteMateria(String fonteMateria) {
        this.fonteMateria = fonteMateria;
    }

}
