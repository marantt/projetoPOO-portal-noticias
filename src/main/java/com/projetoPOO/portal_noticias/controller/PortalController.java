package com.projetoPOO.portal_noticias.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projetoPOO.portal_noticias.modelo.Noticia;
import com.projetoPOO.portal_noticias.servico.PortalService;

@Controller
public class PortalController {

    @Autowired
    private PortalService portalService;

    @GetMapping("/portal") 
    public String exibirPaginaPortal(Model model) { 
        
        List<Noticia> todasAsNoticias = portalService.buscarNoticias();
        
        model.addAttribute("noticias", todasAsNoticias);
        
        return "portal"; 
    }
}
