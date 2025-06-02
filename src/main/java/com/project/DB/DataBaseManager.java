package com.project.DB;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {
    Connection conn = null;
    String user = "";
    String pass = "";

    public DataBaseManager(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public void conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres"; // url padrão para servidor postgres
            conn = DriverManager.getConnection(url, user, pass); // cria a conexao
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void criarBanco() {
        try {
            Statement stmt = conn.createStatement();

            String verificacao = "SELECT 1 FROM pg_database WHERE datname = 'ubus_data'";
            ResultSet rs = stmt.executeQuery(verificacao);

            if (!rs.next()) { // se o bd nao existir, cria
                String nomeBanco = "ubus_data";
                String sql = "CREATE DATABASE " + nomeBanco;
                stmt.executeUpdate(sql);

                System.out.println("Banco de dados criado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void criarTabelas() {
        try {
            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();

            // Tabela de usuários base
            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL UNIQUE, " +
                    "senha VARCHAR(100) NOT NULL" +
                    ")";
            stmt.executeUpdate(sqlUsers);

            // Motorista
            String sqlMotoristas = "CREATE TABLE IF NOT EXISTS motorista (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INTEGER UNIQUE REFERENCES users(id) ON DELETE CASCADE, " +
                    "cpf VARCHAR(14) UNIQUE NOT NULL, " +
                    "cnh VARCHAR(20) UNIQUE NOT NULL, " +
                    "categoria_cnh VARCHAR(2) NOT NULL, " +
                    "telefone VARCHAR(20), " +
                    "email VARCHAR(100), " +
                    "endereco TEXT" +
                    ")";
            stmt.executeUpdate(sqlMotoristas);

            // Administrador
            String sqlAdmin = "CREATE TABLE IF NOT EXISTS admin (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INTEGER UNIQUE REFERENCES users(id) ON DELETE CASCADE, " +
                    "email VARCHAR(100)" +
                    ")";
            stmt.executeUpdate(sqlAdmin);

            // Aluno
            String sqlAlunos = "CREATE TABLE IF NOT EXISTS alunos (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INTEGER UNIQUE REFERENCES users(id) ON DELETE CASCADE, " +
                    "nome_aluno VARCHAR(100) NOT NULL, " + 
                    "sobrenome_aluno VARCHAR(100) NOT NULL, " +
                    "cpf VARCHAR(14) UNIQUE NOT NULL, " +
                    "cep VARCHAR(9) NOT NULL, " +
                    "endereco TEXT NOT NULL, " +
                    "bairro VARCHAR(50) NOT NULL, " +
                    "curso VARCHAR(50) NOT NULL, " +
                    "semestre INTEGER NOT NULL, " +
                    "turno VARCHAR(20) NOT NULL, " +
                    "instituicao VARCHAR(100) NOT NULL, " +
                    "telefone VARCHAR(20) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL, " +
                    "vai_para_aula BOOLEAN DEFAULT FALSE, " +
                    "data_ultima_presenca TIMESTAMP" +
                    ")";
            stmt.executeUpdate(sqlAlunos);

            System.out.println("Tabelas criadas/verificadas com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO ao criar tabelas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.println("ERRO ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ubus_data", user, pass);
    }

}