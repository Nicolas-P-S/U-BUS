package com.project.DB;
import com.project.entity.Usuario;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {
    Connection conn = null;
    String user = "";
    String pass = "";
    public DataBaseManager(String user, String pass){
        this.user = user;
        this.pass = pass;
    }

    public void conectar(){
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres"; // url padrão para servidor postgres
            conn = DriverManager.getConnection(url, user, pass);

            System.out.println("Conectado!");
        } catch (SQLException e) {
            System.out.println("ERRO: "+ e.getMessage());
        }
    }

    public void criarBanco(){
        try {
            Statement stmt = conn.createStatement();

            String verificacao = "SELECT 1 FROM pg_database WHERE datname = 'teste'";
            ResultSet rs = stmt.executeQuery(verificacao);

            if (!rs.next()){
                String nomeBanco = "teste";
                String sql = "CREATE DATABASE " + nomeBanco;
                stmt.executeUpdate(sql);
    
                System.out.println("Banco de dados criado com sucesso!");
                criarTabelas();
            }

        } catch (Exception e) {
            System.out.println("ERRO: "+ e.getMessage());
        }
    }

    public void criarTabelas(){
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE users (id SERIAL PRIMARY KEY, nome VARCHAR(20), senha VARCHAR(20))";
            stmt.executeUpdate(sql);
            
            System.out.println("Tabelas criadas com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: "+ e);
        }
    }

    public void salvarUsuario(Usuario usuario){
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            String sql = "INSERT INTO users (nome, senha) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO: "+ e);
        }
    }
}
