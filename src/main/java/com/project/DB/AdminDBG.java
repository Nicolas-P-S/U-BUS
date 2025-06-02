package com.project.DB;

import com.project.entity.Admin;
import com.project.entity.Aluno;
import com.project.entity.Motorista;
import com.project.entity.Usuario;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDBG extends UserDBG{
    
    public AdminDBG(DataBaseManager db) {
        super(db);
    }

    public void salvarAdmin(Admin admin) {
        try {
            int userId = salvarUsuario(new Usuario(admin.getNome(), admin.getSenha()));
            if (userId == -1) {
                System.out.println("ERRO: não foi possível criar o usuário base.");
                return;
            }

            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            String sql = "INSERT INTO admin (user_id, email) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, admin.getEmail());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERRO ao salvar Admin: " + e);
        }
    }

    public Admin pesquisarAdminPorNome(String nome) {
        Admin admin = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            String sql = "SELECT u.id as user_id, u.nome, u.senha, a.email " +
                        "FROM users u " +
                        "JOIN admin a ON u.id = a.user_id " +
                        "WHERE u.nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin(
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("email")
                );
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return admin;
    }

    public Admin buscarAdmin(String nome, String senha) {
        Admin admin = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            String sql = "SELECT u.id as user_id, u.nome, u.senha, a.email " +
                        "FROM users u " +
                        "JOIN admin a ON u.id = a.user_id " +
                        "WHERE u.nome = ? AND u.senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setNome(rs.getString("nome"));
                admin.setSenha(rs.getString("senha"));
                admin.setEmail(rs.getString("email"));
            }

        } catch (Exception e) {
            System.out.println("ERRO ao buscar admin: " + e.getMessage());
        }
        return admin;
    }
}
