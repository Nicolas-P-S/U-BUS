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

            String sqlUsers = "CREATE TABLE IF NOT EXISTS usuario (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL UNIQUE, " +
                    "senha VARCHAR(100) NOT NULL" +
                    ")";
            stmt.executeUpdate(sqlUsers);

            String sqlMotoristas = "CREATE TABLE IF NOT EXISTS motorista ("+
                    "id_Motorista SERIAL PRIMARY KEY,"+
                    "cpf VARCHAR(45) UNIQUE,"+
                    "categoria VARCHAR(45),"+
                    "telefone VARCHAR(45),"+
                    "email VARCHAR(45),"+
                    "endereco VARCHAR(45),"+
                    "cnh VARCHAR(45),"+
                    "usuario_id INT REFERENCES usuario(id),"+
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
                    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                    ")";
            stmt.executeUpdate(sqlMotoristas);

            String sqlAdmin = "CREATE TABLE IF NOT EXISTS admin ("+
                    "id SERIAL PRIMARY KEY,"+
                    "usuario_id INT UNIQUE REFERENCES usuario(id),"+
                    "email VARCHAR(100) NOT NULL,"+
                    "senha VARCHAR(100) NOT NULL,"+
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
                    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                    ")";
            stmt.executeUpdate(sqlAdmin);

            String sqlInstituicao = "CREATE TABLE IF NOT EXISTS instituicao ("+
                    "id SERIAL PRIMARY KEY,"+
                    "nome VARCHAR(100) UNIQUE NOT NULL,"+
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
                    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                    ")";
            stmt.executeUpdate(sqlInstituicao);

            String sqlAlunos = "CREATE TABLE IF NOT EXISTS aluno ("+
                    "id SERIAL PRIMARY KEY,"+
                    "nome_aluno VARCHAR(100) NOT NULL,"+
                    "sobrenome_aluno VARCHAR(100) NOT NULL,"+
                    "cpf VARCHAR(14) UNIQUE,"+
                    "curso VARCHAR(100),"+
                    "semestre INT,"+
                    "turno VARCHAR(20),"+
                    "rua VARCHAR(100),"+
                    "bairro VARCHAR(50),"+
                    "cep VARCHAR(10),"+
                    "telefone VARCHAR(20),"+
                    "email VARCHAR(100),"+
                    "instituicao_id INT REFERENCES instituicao(id),"+
                    "usuario_id INT UNIQUE REFERENCES usuario(id),"+
                    "vai_para_aula BOOLEAN DEFAULT FALSE,"+
                    "data_ultima_presenca TIMESTAMP,"+
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
                    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
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