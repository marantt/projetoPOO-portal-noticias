package com.projetoPOO.portal_noticias.modelo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/* Coisas relacionadas a senha e hash pedi ajuda a IA, espero que esteja funcionando! */
// Acabou que nem conseguimos terminar :c
public class UsuarioAdmin {

    private String username;
    private String password;
    private boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UsuarioAdmin() {
        this.ativo = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public UsuarioAdmin(String username, String passwordHash, boolean ativo,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.username = username;
        this.password = passwordHash;
        this.ativo = ativo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UsuarioAdmin(String username, String plainPassword) {
        this.username = username;
        this.password = hash(plainPassword);
        this.ativo = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String plainPassword) {
        this.password = hash(plainPassword);
    }

    void setPasswordHashDirect(String hash) {
        this.password = hash;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean senhaConfere(String plainPassword) {
        String hashed = hash(plainPassword);
        return hashed != null && hashed.equals(this.password);
    }

    private String hash(String input) {
        if (input == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao calcular hash da senha", e);
        }
    }
}
