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

            String verificacao = "SELECT 1 FROM pg_database WHERE datname = 'teste'";
            ResultSet rs = stmt.executeQuery(verificacao);

            if (!rs.next()) { // se o bd nao existir, cria
                String nomeBanco = "teste";
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
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            Statement stmt = conn.createStatement();

            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "senha VARCHAR(100) NOT NULL, " +
                    "tipo VARCHAR(20) NOT NULL DEFAULT 'usuario'" +
                    ")";
            stmt.executeUpdate(sqlUsers);

            String sqlMotoristas = "CREATE TABLE IF NOT EXISTS motorista (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "senha VARCHAR(100) NOT NULL, " +
                    "cpf VARCHAR(14) UNIQUE NOT NULL, " +
                    "cnh VARCHAR(20) UNIQUE NOT NULL, " +
                    "categoria_cnh VARCHAR(2) NOT NULL, " +
                    "telefone VARCHAR(20), " +
                    "email VARCHAR(100), " +
                    "endereco TEXT" +
                    ")";
            stmt.executeUpdate(sqlMotoristas);

            String sqlAdmin = "CREATE TABLE IF NOT EXISTS admin (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "senha VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100), " +
                    "tipo VARCHAR(100)" +
                    ")";
            stmt.executeUpdate(sqlAdmin);

            String sqlAlunos = "CREATE TABLE IF NOT EXISTS alunos (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "senha VARCHAR(100) NOT NULL, " +
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

            System.out.println("Tabelas verificadas/criadas com sucesso!");
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

    public void salvarAdmin(Admin admin) {
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            String sql = "INSERT INTO admin (nome, senha, email, tipo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getNome());
            stmt.setString(2, admin.getSenha());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getTipo());
            stmt.executeUpdate();

            salvarUsuario(new Usuario(admin.getNome(), admin.getSenha()));

        } catch (Exception e) {
            System.out.println("ERRO ao salvar Adiministrador: " + e);
        }
    }

public void salvarAluno(Aluno aluno) {
    try {
        String url = "jdbc:postgresql://localhost:5432/teste";
        conn = DriverManager.getConnection(url, user, pass);

        String sql = "INSERT INTO alunos (" +
                "nome, senha, nome_aluno, sobrenome_aluno, cpf, cep, endereco, bairro, curso, " +
                "semestre, turno, instituicao, telefone, email, vai_para_aula" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, aluno.getNome()); // nome de usuário
        stmt.setString(2, aluno.getSenha());
        stmt.setString(3, aluno.getNome_Aluno()); // nome do aluno
        stmt.setString(4, aluno.getSobrenome_Aluno()); // sobrenome do aluno
        stmt.setString(5, aluno.getCpf());
        stmt.setString(6, aluno.getCep());
        stmt.setString(7, aluno.getEnderco());
        stmt.setString(8, aluno.getBairro());
        stmt.setString(9, aluno.getCurso());
        stmt.setInt(10, aluno.getSemestre());
        stmt.setString(11, aluno.getTurno());
        stmt.setString(12, aluno.getInstituicao());
        stmt.setString(13, aluno.getTelefone());
        stmt.setString(14, aluno.getEmail());
        stmt.setBoolean(15, aluno.isVaiParaAula());

        stmt.executeUpdate();

        salvarUsuario(new Usuario(aluno.getNome(), aluno.getSenha()));
    } catch (Exception e) {
        System.out.println("ERRO ao salvar aluno: " + e);
    }
}

public Aluno buscarAluno(String nome, String senha) {
    Aluno aluno = null;
    try {
        String url = "jdbc:postgresql://localhost:5432/teste";
        conn = DriverManager.getConnection(url, user, pass);

        String sql = "SELECT * FROM alunos WHERE nome = ? AND senha = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            aluno = new Aluno(
                    rs.getString("nome"), // nome de usuário
                    rs.getString("senha"),
                    rs.getString("nome_aluno"), // nome do aluno
                    rs.getString("sobrenome_aluno"), // sobrenome do aluno
                    rs.getString("cpf"),
                    rs.getString("cep"),
                    rs.getString("endereco"),
                    rs.getString("bairro"),
                    rs.getString("curso"),
                    rs.getInt("semestre"),
                    rs.getString("turno"),
                    rs.getString("instituicao"),
                    rs.getString("telefone"),
                    rs.getString("email"));

            try {
                aluno.setVaiParaAula(rs.getBoolean("vai_para_aula"));
            } catch (SQLException e) {
                System.out.println("Aviso: Coluna vai_para_aula não encontrada, usando valor padrão false");
                aluno.setVaiParaAula(false);
            }
        }
    } catch (Exception e) {
        System.out.println("ERRO ao buscar aluno: " + e.getMessage());
    }
    return aluno;
}

    public Admin pesquisarAdminPorNome(String nome) {
        Admin admin = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            String sql = "SELECT * FROM admin WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin(
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getString("tipo"));
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return admin;
    }

 public Aluno pesquisarAlunoPorCpf(String cpf) {
    Aluno aluno = null;
    try {
        String url = "jdbc:postgresql://localhost:5432/teste";
        conn = DriverManager.getConnection(url, user, pass);

        String sql = "SELECT * FROM alunos WHERE cpf = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            aluno = new Aluno(
                    rs.getString("nome"), // nome de usuário
                    rs.getString("senha"),
                    rs.getString("nome_aluno"), // nome do aluno
                    rs.getString("sobrenome_aluno"), // sobrenome do aluno
                    rs.getString("cpf"),
                    rs.getString("cep"),
                    rs.getString("endereco"),
                    rs.getString("bairro"),
                    rs.getString("curso"),
                    rs.getInt("semestre"),
                    rs.getString("turno"),
                    rs.getString("instituicao"),
                    rs.getString("telefone"),
                    rs.getString("email"));

            try {
                aluno.setVaiParaAula(rs.getBoolean("vai_para_aula"));
            } catch (SQLException e) {
                System.out.println("Aviso: Coluna vai_para_aula não encontrada, usando valor padrão false");
                aluno.setVaiParaAula(false);
            }
        }
    } catch (Exception e) {
        System.out.println("ERRO ao pesquisar aluno por CPF: " + e.getMessage());
    }
    return aluno;
}

public List<Aluno> consultarAlunos() {
    List<Aluno> lista = new ArrayList<>();
    try {
        String url = "jdbc:postgresql://localhost:5432/teste";
        conn = DriverManager.getConnection(url, user, pass);

        String sql = "SELECT nome, senha, nome_aluno, sobrenome_aluno, cpf, cep, endereco, bairro, curso, " +
                "semestre, turno, instituicao, telefone, email, vai_para_aula " +
                "FROM alunos";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Aluno aluno = new Aluno(
                    rs.getString("nome"), // nome de usuário
                    rs.getString("senha"),
                    rs.getString("nome_aluno"), // nome do aluno
                    rs.getString("sobrenome_aluno"), // sobrenome do aluno
                    rs.getString("cpf"),
                    rs.getString("cep"),
                    rs.getString("endereco"),
                    rs.getString("bairro"),
                    rs.getString("curso"),
                    rs.getInt("semestre"),
                    rs.getString("turno"),
                    rs.getString("instituicao"),
                    rs.getString("telefone"),
                    rs.getString("email"));

            aluno.setVaiParaAula(rs.getBoolean("vai_para_aula"));

            lista.add(aluno);
        }
    } catch (Exception e) {
        System.out.println("ERRO ao consultar alunos: " + e.getMessage());
    }
    return lista;
}

    public boolean atualizarPresencaAluno(String cpf, boolean vaiParaAula) {
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            String verifica = "SELECT 1 FROM alunos WHERE cpf = ?";
            PreparedStatement stmtVerifica = conn.prepareStatement(verifica);
            stmtVerifica.setString(1, cpf);
            ResultSet rs = stmtVerifica.executeQuery();

            if (!rs.next()) {
                return false;
            }

            String sql = "UPDATE alunos SET vai_para_aula = ?, data_ultima_presenca = CURRENT_TIMESTAMP WHERE cpf = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, vaiParaAula);
            stmt.setString(2, cpf);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("ERRO ao atualizar presença do aluno: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.println("ERRO ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public void salvarMotorista(Motorista motorista) {
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            String sql = "INSERT INTO motorista (nome, senha, cpf, cnh, categoria_cnh, telefone, email, endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getSenha());
            stmt.setString(3, motorista.getCpf());
            stmt.setString(4, motorista.getCnh());
            stmt.setString(5, motorista.getCategoriaCNH());
            stmt.setString(6, motorista.getTelefone());
            stmt.setString(7, motorista.getEmail());
            stmt.setString(8, motorista.getEndereco());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO ao salvar motorista: " + e);
        }
    }

    public Admin buscarAdmin(String nome, String senha) {
        Admin admin = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            String sql = "SELECT * FROM admin WHERE nome = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setNome(rs.getString("nome"));
                admin.setSenha(rs.getString("senha"));
                admin.setEmail(rs.getString("email"));
                admin.setTipo(rs.getString("tipo"));
            }
        } catch (Exception e) {
            System.out.println("ERRO ao buscar admin: " + e.getMessage());
        }
        return admin;
    }

    public List<Motorista> consultarMotoristas() {
        List<Motorista> lista = new ArrayList<>();
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            String sql = "SELECT * FROM motorista";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Motorista m = new Motorista(
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("cnh"),
                        rs.getString("categoria_cnh"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco"));
                lista.add(m);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao consultar motoristas: " + e.getMessage());
        }
        return lista;
    }

    public void salvarUsuario(Usuario usuario) {
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

            String sql = "INSERT INTO users (nome, senha) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
    }

    public Usuario pesquisarUsuarioNome(String nome) {
        Usuario usuario = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

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
            String url = "jdbc:postgresql://localhost:5432/teste";
            conn = DriverManager.getConnection(url, user, pass);

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
