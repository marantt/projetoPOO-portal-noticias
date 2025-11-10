package com.projetoPOO.portal_noticias.modelo;

public class NoticiaTextoSimples extends Noticia {

    // Atributo específico para NoticiaTextoSimples
    private String fonteMateria;

    // Construtor
    public NoticiaTextoSimples(String titulo, String conteudo, Autor autor, String fonteMateria) {
        super(titulo, conteudo, autor);
        this.fonteMateria = fonteMateria;
    }

    // Método para gerar a exibição da notícia ***** criei so pra exemplo  ****
    @Override
    public String exibirNoticia() {
        StringBuilder html = new StringBuilder();
        html.append("<h1>").append(getTitulo()).append("</h1>");
        html.append("<p>").append(getConteudo()).append("</p>");
        html.append("<p><strong>Autor:</strong> ").append(getAutor().getNome()).append("</p>");
        html.append("<p><strong>Fonte:</strong> ").append(fonteMateria).append("</p>");
        return html.toString();
    }

    // Getter
    public String getFonteMateria() {
        return fonteMateria;
    }

}
