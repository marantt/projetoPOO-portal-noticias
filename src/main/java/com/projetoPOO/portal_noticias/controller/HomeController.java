package com.projetoPOO.portal_noticias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.projetoPOO.portal_noticias.modelo.AutorDAO;
import com.projetoPOO.portal_noticias.modelo.NoticiaDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private NoticiaDAO noticiaDAO;

    @Autowired
    private AutorDAO autorDAO;


    @GetMapping("/")
    public String home(Model model) {
    try {
        model.addAttribute("noticias", noticiaDAO.listarTodas());
    } catch (SQLException e) {
        model.addAttribute("noticias", java.util.Collections.emptyList());
    }
    return "index";
}


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/criarUsuario")
    public String criarUsuarioForm() {
        return "criarUsuario";
    }

    @GetMapping("/resetSenha")
    public String resetSenhaForm() {
        return "resetSenha";
    }

    @GetMapping("/admin")
    public String admin(HttpSession session, Model model) {
        try {
            List<com.projetoPOO.portal_noticias.modelo.Noticia> noticias = noticiaDAO.listarTodas();
            model.addAttribute("noticias", noticias);
        } catch (SQLException e) {
            model.addAttribute("noticias", java.util.Collections.emptyList());
        }

        return "admin"; 
    }

    @GetMapping("/criarNoticia")
    public String criarNoticiaForm(Model model) {
    try {
        model.addAttribute("autores", autorDAO.listarTodos());
    } catch (SQLException e) {
        model.addAttribute("erro", "Erro ao carregar autores.");
        model.addAttribute("autores", java.util.Collections.emptyList());
    }
    return "criarNoticia";
    }

    @GetMapping("/noticia/{id}/editar")
    public String editarNoticia(int id, Model model) {
        try {
            var noticia = noticiaDAO.buscarPorId(id);
            model.addAttribute("noticia", noticia);
        } catch (SQLException e) {
            return "redirect:/admin";
        }
        return "editarNoticia";
    }
}
