package com.projetoPOO.portal_noticias.controller;

import com.projetoPOO.portal_noticias.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.sql.SQLException;

@Controller
public class NoticiasController {

    @Autowired
    private NoticiaDAO noticiaDAO;

    @PostMapping("/salvarNoticia")
    public String salvar(@RequestParam("titulo") String titulo,
                         @RequestParam("conteudo") String conteudo,
                         @RequestParam(value = "tipo", defaultValue = "Texto") String tipo,
                         @RequestParam(value = "fonte", required = false) String fonte,
                         @RequestParam(value = "url_foto", required = false) String urlFoto,
                         @RequestParam(value = "url_video", required = false) String urlVideo,
                         RedirectAttributes r) {

        try {
            Autor autor = new Autor();
            autor.setId(1);

            if (tipo.equalsIgnoreCase("Foto")) {
                var n = new NoticiaComFoto(titulo, conteudo, autor, urlFoto);
                noticiaDAO.inserirNoticiaComFoto(n);
            } else if (tipo.equalsIgnoreCase("Video") || tipo.equalsIgnoreCase("Vídeo")) {
                var n = new NoticiaComVideo(titulo, conteudo, autor, urlVideo);
                noticiaDAO.inserirNoticiaComVideo(n);
            } else if (tipo.equalsIgnoreCase("Urgente")) {
                var n = new NoticiaUrgente(titulo, conteudo, autor);
                noticiaDAO.inserirNoticiaUrgente(n);
            } else {
                var n = new NoticiaTextoSimples(
                        titulo,
                        conteudo,
                        autor,
                        (fonte != null && !fonte.isEmpty()) ? fonte : "Portal"
                );
                noticiaDAO.inserirNoticiaTextoSimples(n);
            }

            r.addFlashAttribute("mensagem", "Notícia salva com sucesso!");
            return "redirect:/admin";

        } catch (SQLException e) {
            r.addFlashAttribute("erro", "Erro ao salvar notícia.");
            return "redirect:/criarNoticia";
        }
    }

    @PostMapping("/noticia/{id}/atualizar")
    public String atualizar(@PathVariable("id") int id,
                            @RequestParam("titulo") String titulo,
                            @RequestParam("conteudo") String conteudo,
                            RedirectAttributes r) {
        try {
            noticiaDAO.atualizar(id, titulo, conteudo);
            r.addFlashAttribute("mensagem", "Notícia atualizada com sucesso");
        } catch (SQLException e) {
            r.addFlashAttribute("erro", "Erro ao atualizar notícia.");
        }
        return "redirect:/admin";
    }

    @PostMapping("/noticia/{id}/deletar")
    public String deletar(@PathVariable("id") int id, RedirectAttributes r) {
        try {
            noticiaDAO.deletar(id);
            r.addFlashAttribute("mensagem", "Notícia excluída");
        } catch (SQLException e) {
            r.addFlashAttribute("erro", "Erro ao excluir notícia.");
        }
        return "redirect:/admin";
    }
}
