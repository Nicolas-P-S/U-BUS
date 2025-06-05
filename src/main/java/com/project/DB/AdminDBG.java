package com.project.DB;

import com.project.entity.Admin;
import com.project.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AdminDBG extends UserDBG{
    
    public AdminDBG(DataBaseManager db) {
        super(db);
    }

    public void salvarAdmin(Admin admin) {
        try (Connection conn = db.getConnection()){
            int userId = salvarUsuario(new Usuario(admin.getNome(), admin.getSenha()));
            if (userId == -1) {
                System.out.println("ERRO: não foi possível criar o usuário base.");
                return;
            }

            String sql = "INSERT INTO admin (usuario_id, email, senha) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getSenha());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERRO ao salvar Admin: " + e);
        }
    }

    public Admin pesquisarAdminPorNome(String nome) {
        Admin admin = null;
        try (Connection conn = db.getConnection()){
            String sql = "SELECT u.id as user_id, u.nome, u.senha, a.email " +
                        "FROM usuario u " +
                        "JOIN admin a ON u.id = a.usuario_id " +
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
        try (Connection conn = db.getConnection()){
            String sql = "SELECT u.id as user_id, u.nome, u.senha, a.email " +
                        "FROM usuario u " +
                        "JOIN admin a ON u.id = a.usuario_id " +
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
