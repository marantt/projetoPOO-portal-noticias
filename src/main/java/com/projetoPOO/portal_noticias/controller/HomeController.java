package com.projetoPOO.portal_noticias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    
    // @GetMapping("/")
    // public String home() {
    //    return "index";
    // }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/admin";
    }

    @PostMapping("/logout")
    public String logoutPost() {
        return "redirect:/";
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
