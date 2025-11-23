package com.projetoPOO.portal_noticias.controller;

import com.projetoPOO.portal_noticias.modelo.UsuarioAdmin;
import com.projetoPOO.portal_noticias.modelo.UsuarioAdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.sql.SQLException;

@Controller
public class AdminController {

    @Autowired
    private UsuarioAdminDAO usuarioAdminDAO;

    @PostMapping("/criarUsuario")
    public String criarUsuario(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes r) {
        try {
            UsuarioAdmin u = new UsuarioAdmin(username, password);
            usuarioAdminDAO.inserir(u);
            r.addFlashAttribute("mensagem", "Usuário criado com sucesso!");
            return "redirect:/admin";
        } catch (SQLException e) {
            r.addFlashAttribute("erro", "Erro ao criar usuário.");
            return "redirect:/criarUsuario";
        }
    }

    @PostMapping("/resetSenha")
    public String resetSenha(@RequestParam("username") String username,
                             @RequestParam("newPassword") String novaSenha,
                             RedirectAttributes r) {
        try {
            var u = usuarioAdminDAO.buscarPorUsername(username);
            if (u == null) {
                r.addFlashAttribute("erro", "Usuário não encontrado.");
                return "redirect:/resetSenha";
            }

            usuarioAdminDAO.resetSenha(username, novaSenha);
            r.addFlashAttribute("mensagem", "Senha alterada com sucesso!");
            return "redirect:/login";
        } catch (SQLException e) {
            r.addFlashAttribute("erro", "Erro ao resetar senha.");
            return "redirect:/resetSenha";
        }
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/admin";
    }

    @PostMapping("/logout")
    public String logoutPost() {
        return "redirect:/login";
    }
}
