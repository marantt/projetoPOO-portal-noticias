package com.projetoPOO.portal_noticias.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetoPOO.portal_noticias.modelo.Autor;
import com.projetoPOO.portal_noticias.modelo.Noticia;
import com.projetoPOO.portal_noticias.modelo.NoticiaComFoto;
import com.projetoPOO.portal_noticias.modelo.NoticiaComVideo;
import com.projetoPOO.portal_noticias.modelo.NoticiaTextoSimples;
import com.projetoPOO.portal_noticias.modelo.NoticiaUrgente;

@Service
public class PortalService {
    
    // teste 

    public List<Noticia> buscarNoticias() {
        List<Noticia> noticias = new ArrayList<>();

        Autor autor1 = new Autor("Marco Silva", "marco.silva@example.com");
        Autor autor2 = new Autor("Isabel Tavora", "bel.tavora@example.com");

        Noticia noticia1 = new NoticiaTextoSimples("Tecnologia Avançada", "A tecnologia está avançando rapidamente em várias áreas.", autor1, "TechNews");
        Noticia noticia2 = new NoticiaComFoto("Natureza Deslumbrante", "Uma imagem incrível da natureza.", autor2, "https://natureconservancy-h.assetsadobe.com/is/image/content/dam/tnc/nature/en/photos/t/n/tnc_52987642_Full.jpg?crop=0%2C233%2C4000%2C2200&wid=1300&hei=715&scl=3.076923076923077");
        Noticia noticia3 = new NoticiaComVideo("Novidades em Ciência", "Confira as últimas descobertas científicas.", autor1, "http://example.com/video.mp4");
        Noticia noticia4 = new NoticiaUrgente("Alerta de Segurança", "Um alerta importante sobre segurança cibernética.", autor2);

        noticias.add(noticia1);
        noticias.add(noticia2); 
        noticias.add(noticia3);
        noticias.add(noticia4);

        return noticias;
    }


}
