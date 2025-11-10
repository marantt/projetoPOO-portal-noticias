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

    // Getter
    public String getFonteMateria() {
        return fonteMateria;
    }

}
