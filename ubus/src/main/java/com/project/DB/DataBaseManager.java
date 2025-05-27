package com.project.DB;
import com.project.entity.Admin;
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
    public DataBaseManager(String user, String pass){
        this.user = user;
        this.pass = pass;
    }

    public void conectar(){
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres"; // url padrão para servidor postgres
            conn = DriverManager.getConnection(url, user, pass); // cria a conexao
        } catch (SQLException e) {
            System.out.println("ERRO: "+ e.getMessage());
        }
    }

    public void criarBanco(){
        try {
            Statement stmt = conn.createStatement();
    
            String verificacao = "SELECT 1 FROM pg_database WHERE datname = 'teste'";
            ResultSet rs = stmt.executeQuery(verificacao);
    
            if (!rs.next()){ // se o bd nao existir, cria
                String nomeBanco = "teste";
                String sql = "CREATE DATABASE " + nomeBanco;
                stmt.executeUpdate(sql);
    
                System.out.println("Banco de dados criado com sucesso!");
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
    
            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, nome VARCHAR(20), senha VARCHAR(20))";
            stmt.executeUpdate(sqlUsers);
    
            String sqlMotoristas = """
                CREATE TABLE IF NOT EXISTS motorista (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    senha VARCHAR(100) NOT NULL,
                    cpf VARCHAR(14) UNIQUE NOT NULL,
                    cnh VARCHAR(20) UNIQUE NOT NULL,
                    categoria_cnh VARCHAR(2) NOT NULL,
                    telefone VARCHAR(20),
                    email VARCHAR(100),
                    endereco TEXT
                )
            """;
            stmt.executeUpdate(sqlMotoristas);

            String sqlAdmin = """
                    CREATE TABLE IF NOT EXISTS admin (
                    id SERIAL PRIMARY KEY,
                    nome varchar(100) NOT NULL,
                    senha varchar(100) NOT NULL,
                    email varchar(100),
                    tipo varchar(100)
                    )
                    """;
                    stmt.executeUpdate(sqlAdmin);
    
            System.out.println("Tabelas criadas com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: "+ e);
        }
    }

    public void salvarAdmin(Admin admin){
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
                    rs.getString("tipo")
                );
            }
    
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return admin;
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
                rs.getString("endereco")
            );
            lista.add(m);
        }
    } catch (Exception e) {
        System.out.println("ERRO ao consultar motoristas: " + e.getMessage());
    }
    return lista;
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

    public Usuario pesquisarUsuarioNome(String nome){
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
            System.out.println("ERRO: "+e);
        }
        return usuario;
    }

    public Usuario pesquisarUsuarioSenha(String senha){
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
            System.out.println("ERRO: "+e);
        }
        return usuario;
    }
}
