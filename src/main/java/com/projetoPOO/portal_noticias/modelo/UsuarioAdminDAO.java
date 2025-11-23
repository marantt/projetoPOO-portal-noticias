package com.projetoPOO.portal_noticias.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioAdminDAO {

    @Autowired
    private DataSource dataSource;

    public void inserir(UsuarioAdmin u) throws SQLException {
        String sql = "INSERT INTO usuarios_admin (username, password, ativo, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setBoolean(3, u.isAtivo());
            stmt.setTimestamp(4, Timestamp.valueOf(u.getCreatedAt()));
            stmt.setTimestamp(5, Timestamp.valueOf(u.getUpdatedAt()));
            stmt.executeUpdate();
        }
    }

    public UsuarioAdmin buscarPorUsername(String username) throws SQLException {
        String sql = "SELECT * FROM usuarios_admin WHERE username = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? construir(rs) : null;
            }
        }
    }

    public void resetSenha(String username, String novaSenha) throws SQLException {
        String sql = "UPDATE usuarios_admin SET password = ?, updated_at = now() WHERE username = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaSenha);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
    }

    public List<UsuarioAdmin> listarTodos() throws SQLException {
        String sql = "SELECT * FROM usuarios_admin ORDER BY created_at DESC";
        List<UsuarioAdmin> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(construir(rs));
            }
        }
        return lista;
    }

    public void setAtivo(String username, boolean ativo) throws SQLException {
        String sql = "UPDATE usuarios_admin SET ativo = ?, updated_at = now() WHERE username = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, ativo);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
    }

    public void deletar(String username) throws SQLException {
        String sql = "DELETE FROM usuarios_admin WHERE username = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }

    private UsuarioAdmin construir(ResultSet rs) throws SQLException {
        Timestamp c = rs.getTimestamp("created_at");
        Timestamp u = rs.getTimestamp("updated_at");

        LocalDateTime created = c != null ? c.toLocalDateTime() : LocalDateTime.now();
        LocalDateTime updated = u != null ? u.toLocalDateTime() : LocalDateTime.now();

        return new UsuarioAdmin(
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("ativo"),
                created,
                updated
        );
    }
}
