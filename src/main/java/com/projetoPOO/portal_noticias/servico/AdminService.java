package com.projetoPOO.portal_noticias.servico;

import com.projetoPOO.portal_noticias.modelo.UsuarioAdmin;
import com.projetoPOO.portal_noticias.modelo.UsuarioAdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UsuarioAdminDAO usuarioAdminDAO;

    public UsuarioAdmin buscarPorUsername(String username) {
        try {
            return usuarioAdminDAO.buscarPorUsername(username);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuario admin: " + e.getMessage());
            return null;
        }
    }

    public boolean inserir(UsuarioAdmin u) {
        try {
            usuarioAdminDAO.inserir(u);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuario admin: " + e.getMessage());
            return false;
        }
    }

    public boolean resetSenha(String username, String novaSenha) {
        try {
            usuarioAdminDAO.resetSenha(username, novaSenha);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao resetar senha admin: " + e.getMessage());
            return false;
        }
    }

    public List<UsuarioAdmin> listarTodos() {
        try {
            return usuarioAdminDAO.listarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar admins: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean setAtivo(String username, boolean ativo) {
        try {
            usuarioAdminDAO.setAtivo(username, ativo);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar ativo admin: " + e.getMessage());
            return false;
        }
    }

    public boolean deletar(String username) {
        try {
            usuarioAdminDAO.deletar(username);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar admin: " + e.getMessage());
            return false;
        }
    }
}
