package com.projetoPOO.portal_noticias.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticiaDAO {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AutorDAO autorDAO;

    // Inserir notícia de texto simples
    public void inserirNoticiaTextoSimples(NoticiaTextoSimples noticia) throws SQLException {
        String sql = "INSERT INTO noticias (titulo, conteudo, data_publicacao, autor_id, tipo_noticia, fonte_materia, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getConteudo());
            stmt.setTimestamp(3, Timestamp.valueOf(noticia.getDataPublicacao()));
            stmt.setInt(4, noticia.getAutor().getId());
            stmt.setString(5, "Texto");
            stmt.setString(6, noticia.getFonteMateria());
            stmt.setString(7, "published");
            stmt.executeUpdate();
        }
    }

    // Inserir notícia com foto
    public void inserirNoticiaComFoto(NoticiaComFoto noticia) throws SQLException {
        String sql = "INSERT INTO noticias (titulo, conteudo, data_publicacao, autor_id, tipo_noticia, url_foto, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getConteudo());
            stmt.setTimestamp(3, Timestamp.valueOf(noticia.getDataPublicacao()));
            stmt.setInt(4, noticia.getAutor().getId());
            stmt.setString(5, "Foto");
            stmt.setString(6, noticia.getUrlFoto());
            stmt.setString(7, "published");
            stmt.executeUpdate();
        }
    }

    // Inserir notícia com vídeo
    public void inserirNoticiaComVideo(NoticiaComVideo noticia) throws SQLException {
        String sql = "INSERT INTO noticias (titulo, conteudo, data_publicacao, autor_id, tipo_noticia, url_video, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getConteudo());
            stmt.setTimestamp(3, Timestamp.valueOf(noticia.getDataPublicacao()));
            stmt.setInt(4, noticia.getAutor().getId());
            stmt.setString(5, "Vídeo");
            stmt.setString(6, noticia.getUrlVideo());
            stmt.setString(7, "published");
            stmt.executeUpdate();
        }
    }

    // Inserir notícia urgente
    public void inserirNoticiaUrgente(NoticiaUrgente noticia) throws SQLException {
        String sql = "INSERT INTO noticias (titulo, conteudo, data_publicacao, autor_id, tipo_noticia, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getConteudo());
            stmt.setTimestamp(3, Timestamp.valueOf(noticia.getDataPublicacao()));
            stmt.setInt(4, noticia.getAutor().getId());
            stmt.setString(5, "Urgente");
            stmt.setString(6, "published");
            stmt.executeUpdate();
        }
    }

    // Listar todas as notícias (ordenadas)
    public List<Noticia> listarTodas() throws SQLException {
        String sql = "SELECT * FROM noticias ORDER BY data_publicacao DESC";
        List<Noticia> noticias = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                noticias.add(constroiNoticia(rs));
            }
        }
        return noticias;
    }

    // Listar notícias publicadas
    public List<Noticia> listarPublicadas(int limit) throws SQLException {
        String sql = "SELECT * FROM noticias WHERE status = 'published' ORDER BY data_publicacao DESC LIMIT ?";
        List<Noticia> noticias = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    noticias.add(constroiNoticia(rs));
                }
            }
        }
        return noticias;
    }

    // Buscar por ID
    public Noticia buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM noticias WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return constroiNoticia(rs);
                }
            }
        }
        return null;
    }

    // Deletar notícia
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM noticias WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Atualizar título e conteúdo de uma notícia
    public void atualizar(int id, String titulo, String conteudo) throws SQLException {
        String sql = "UPDATE noticias SET titulo = ?, conteudo = ?, updated_at = now() WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, conteudo);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    // Auxiliar para construir objeto Noticia a partir do ResultSet
    private Noticia constroiNoticia(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo_noticia");
        String titulo = rs.getString("titulo");
        String conteudo = rs.getString("conteudo");
        Timestamp ts = rs.getTimestamp("data_publicacao");
        LocalDateTime data = ts != null ? ts.toLocalDateTime() : LocalDateTime.now();
        int autorId = rs.getInt("autor_id");

        Autor autor = null;
        try {
            autor = autorDAO.buscarPorId((long) autorId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Noticia noticia = null;
        if ("Texto".equals(tipo)) {
            String fonte = rs.getString("fonte_materia");
            noticia = new NoticiaTextoSimples(titulo, conteudo, autor, fonte);
        } else if ("Foto".equals(tipo)) {
            String url = rs.getString("url_foto");
            noticia = new NoticiaComFoto(titulo, conteudo, autor, url);
        } else if ("Vídeo".equals(tipo) || "Video".equals(tipo)) {
            String url = rs.getString("url_video");
            noticia = new NoticiaComVideo(titulo, conteudo, autor, url);
        } else if ("Urgente".equals(tipo)) {
            noticia = new NoticiaUrgente(titulo, conteudo, autor);
        } else {
            noticia = new NoticiaTextoSimples(titulo, conteudo, autor, null);
        }

        // preencher id e data
        noticia.setId(rs.getInt("id"));
        noticia.setDataPublicacao(data);

        return noticia;
    }

}
