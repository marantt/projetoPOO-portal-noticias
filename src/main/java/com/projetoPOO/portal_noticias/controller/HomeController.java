package com.projetoPOO.portal_noticias.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoPOO.portal_noticias.modelo.Autor;
import com.projetoPOO.portal_noticias.modelo.NoticiaTextoSimples;
import com.projetoPOO.portal_noticias.modelo.NoticiaDAO;

@Controller
public class HomeController {

    @Autowired
    private NoticiaDAO noticiaDAO;
    
    @GetMapping("/")
    public String home() {
       return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logon")
    public String logonPost() {
        return "redirect:/admin";
    }

    @PostMapping("/logout")
    public String logoutPost() {
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        try {
            List<com.projetoPOO.portal_noticias.modelo.Noticia> noticias = noticiaDAO.listarTodas();
            model.addAttribute("noticias", noticias);
        } catch (SQLException e) {
            model.addAttribute("noticias", java.util.Collections.emptyList());
            System.out.println("Erro ao listar noticias no admin: " + e.getMessage());
        }
        return "admin";
    }

    @GetMapping("/criarNoticia")
    public String criarNoticia() {
        return "criarNoticia";
    }

    @GetMapping("/noticia/{id}/editar")
    public String editarNoticia(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            com.projetoPOO.portal_noticias.modelo.Noticia noticia = noticiaDAO.buscarPorId(id);
            if (noticia == null) {
                redirectAttributes.addFlashAttribute("erro", "Notícia não encontrada");
                return "redirect:/admin";
            }
            model.addAttribute("noticia", noticia);
            return "editarNoticia";
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao abrir edição: " + e.getMessage());
            return "redirect:/admin";
        }
    }

    @PostMapping("/noticia/{id}/atualizar")
    public String atualizarNoticia(@PathVariable("id") int id,
                                  @RequestParam("titulo") String titulo,
                                  @RequestParam("conteudo") String conteudo,
                                  RedirectAttributes redirectAttributes) {
        try {
            noticiaDAO.atualizar(id, titulo, conteudo);
            redirectAttributes.addFlashAttribute("mensagem", "Notícia atualizada com sucesso");
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar notícia: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/noticia/{id}/deletar")
    public String deletarNoticia(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            noticiaDAO.deletar(id);
            redirectAttributes.addFlashAttribute("mensagem", "Notícia excluída");
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir notícia: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/salvarNoticia")
    public String salvarNoticia(
            @RequestParam("titulo") String titulo,
            @RequestParam("conteudo") String conteudo,
            @RequestParam(value = "tipo", required = false, defaultValue = "Texto") String tipo,
            @RequestParam(value = "fonte", required = false) String fonte,
            @RequestParam(value = "url_foto", required = false) String urlFoto,
            @RequestParam(value = "url_video", required = false) String urlVideo,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Criar autor padrão (id=1 deve existir no banco)
            Autor autorPadrao = new Autor();
            autorPadrao.setId(1);
            
            // Criar notícia de acordo com o tipo selecionado
            if ("Foto".equalsIgnoreCase(tipo)) {
                com.projetoPOO.portal_noticias.modelo.NoticiaComFoto n = new com.projetoPOO.portal_noticias.modelo.NoticiaComFoto(
                        titulo, conteudo, autorPadrao, urlFoto);
                noticiaDAO.inserirNoticiaComFoto(n);
            } else if ("Vídeo".equalsIgnoreCase(tipo) || "Video".equalsIgnoreCase(tipo)) {
                com.projetoPOO.portal_noticias.modelo.NoticiaComVideo n = new com.projetoPOO.portal_noticias.modelo.NoticiaComVideo(
                        titulo, conteudo, autorPadrao, urlVideo);
                noticiaDAO.inserirNoticiaComVideo(n);
            } else if ("Urgente".equalsIgnoreCase(tipo)) {
                com.projetoPOO.portal_noticias.modelo.NoticiaUrgente n = new com.projetoPOO.portal_noticias.modelo.NoticiaUrgente(
                        titulo, conteudo, autorPadrao);
                noticiaDAO.inserirNoticiaUrgente(n);
            } else {
                NoticiaTextoSimples noticia = new NoticiaTextoSimples(
                        titulo,
                        conteudo,
                        autorPadrao,
                        (fonte != null && !fonte.isEmpty()) ? fonte : "Portal"
                );
                noticiaDAO.inserirNoticiaTextoSimples(noticia);
            }
            
            redirectAttributes.addFlashAttribute("mensagem", "Notícia salva com sucesso!");
            return "redirect:/admin";
            
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar notícia: " + e.getMessage());
            return "redirect:/criarNoticia";
        }
    }
}
