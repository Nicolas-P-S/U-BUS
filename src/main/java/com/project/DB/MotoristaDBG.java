package com.project.DB;


import com.project.entity.Motorista;
import com.project.entity.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MotoristaDBG extends UserDBG{

    public MotoristaDBG(DataBaseManager db) {
        super(db);
    }

    public void salvarMotorista(Motorista motorista) {
        try {
            int userId = salvarUsuario(new Usuario(motorista.getNome(), motorista.getSenha()));
            if (userId == -1) {
                System.out.println("ERRO: não foi possível criar o usuário base do motorista.");
                return;
            }

            Connection conn = db.getConnection();

            String sql = "INSERT INTO motorista (user_id, cpf, cnh, categoria_cnh, telefone, email, endereco) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, motorista.getCpf());
            stmt.setString(3, motorista.getCnh());
            stmt.setString(4, motorista.getCategoriaCNH());
            stmt.setString(5, motorista.getTelefone());
            stmt.setString(6, motorista.getEmail());
            stmt.setString(7, motorista.getEndereco());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERRO ao salvar motorista: " + e.getMessage());
        }
    }

    public Motorista buscarMotorista(String nome, String senha) {
        Motorista motorista = null;
        try (Connection conn = db.getConnection()){
            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            String sql = "SELECT u.nome, u.senha, m.* " +
                        "FROM users u " +
                        "JOIN motorista m ON u.id = m.user_id " +
                        "WHERE u.nome = ? AND u.senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                motorista = new Motorista(
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getString("cpf"),
                    rs.getString("cnh"),
                    rs.getString("categoria_cnh"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("endereco")
                );
            }

        } catch (Exception e) {
            System.out.println("ERRO ao buscar motorista: " + e.getMessage());
        }
        return motorista;
    }

    public List<Motorista> consultarMotoristas() {
        List<Motorista> lista = new ArrayList<>();
        try (Connection conn = db.getConnection()){
            String url = "jdbc:postgresql://localhost:5432/ubus_data";
            String sql = """
                SELECT m.*, u.nome, u.senha
                FROM motorista m
                JOIN users u ON m.user_id = u.id
            """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Motorista m = new Motorista(
                        rs.getString("nome"), // nome de login (users)
                        rs.getString("senha"),        // senha (users)
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
}
