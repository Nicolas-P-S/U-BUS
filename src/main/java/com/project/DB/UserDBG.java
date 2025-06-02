package com.project.DB;


import com.project.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDBG {
    DataBaseManager db;
    Connection conn;

    public UserDBG(DataBaseManager db){
        this.db = db;
        try {
            this.conn = db.getConnection();    
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int salvarUsuario(Usuario usuario) {
        int userId = -1;
        try {
            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            String sql = "INSERT INTO users (nome, senha) VALUES (?, ?) RETURNING id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("ERRO ao salvar usuário: " + e);
        }
        return userId;
    }


    public Usuario pesquisarUsuarioNome(String nome) {
        Usuario usuario = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            String sql = "SELECT * FROM users WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                usuario = new Usuario(rs.getString("nome"), rs.getString("senha"));

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
        return usuario;
    }

    public Usuario pesquisarUsuarioSenha(String senha) {
        Usuario usuario = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            String sql = "SELECT * FROM users WHERE senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                usuario = new Usuario(rs.getString("nome"), rs.getString("senha"));

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
        return usuario;
    }
}
