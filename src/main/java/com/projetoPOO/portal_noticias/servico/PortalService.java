package com.projetoPOO.portal_noticias.servico;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoPOO.portal_noticias.modelo.Noticia;
import com.projetoPOO.portal_noticias.modelo.NoticiaDAO;

@Service
public class PortalService {

    @Autowired
    private NoticiaDAO noticiaDAO;

    /**
     * Busca notícias publicadas (usa NoticiaDAO). Em caso de erro de acesso ao banco,
     * retorna lista vazia para não quebrar a camada web.
     */
    public List<Noticia> buscarNoticias() {
        try {
            // limite padrão para exibir no portal
            return noticiaDAO.listarPublicadas(10);
        } catch (SQLException e) {
            // log mínimo no console (podemos integrar um logger depois)
            System.out.println("Erro ao buscar noticias no PortalService: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
