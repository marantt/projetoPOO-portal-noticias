package com.projetoPOO.portal_noticias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    
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
    public String admin() {
        return "admin";
    }

    @GetMapping("/criarNoticia")
    public String criarNoticia() {
        return "criarNoticia";
    }
}
