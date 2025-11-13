package com.projetoPOO.portal_noticias.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AutorDAO {

    @Autowired
    private DataSource dataSource;

    // Inserir um novo autor
    public void inserir(Autor autor) throws SQLException {
        String sql = "INSERT INTO autores (nome, email) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEmail());
            stmt.executeUpdate();
        }
    }

    // Buscar autor por ID
    public Autor buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM autores WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id.intValue());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor();                    
                    autor.setId(rs.getInt("id"));              
                    autor.setNome(rs.getString("nome"));
                    autor.setEmail(rs.getString("email"));
                    return autor;
                }
            }
        }
        return null;
    }

    // Listar todos os autores
    public List<Autor> listarTodos() throws SQLException {
        String sql = "SELECT * FROM autores";
        List<Autor> autores = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNome(rs.getString("nome"));
                autor.setEmail(rs.getString("email"));
                autores.add(autor);
            }
        }
        return autores;
    }

    // Atualizar autor
    public void atualizar(Autor autor) throws SQLException {
        String sql = "UPDATE autores SET nome = ?, email = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEmail());
            stmt.setInt(3, autor.getId());
            stmt.executeUpdate();
        }
    }

    // Deletar autor
    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM autores WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id.intValue());
            stmt.executeUpdate();
        }
    }
}