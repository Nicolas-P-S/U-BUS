package com.project.DB;

import com.project.entity.Aluno;
import com.project.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDBG extends UserDBG{
    
    public AlunoDBG(DataBaseManager db) {
        super(db);
    }

    public void salvarAluno(Aluno aluno) {
        int userId = salvarUsuario(new Usuario(aluno.getNome(), aluno.getSenha()));
        if (userId == -1) {
            System.out.println("ERRO: não foi possível criar o usuário base do aluno.");
            return;
        }

        String sql = "INSERT INTO alunos (" +
                    "user_id, nome_aluno, sobrenome_aluno, cpf, cep, endereco, bairro, curso, semestre, turno, instituicao, telefone, email" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userId);
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getSobrenome_Aluno());
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getCep());
            stmt.setString(6, aluno.getEndereco());
            stmt.setString(7, aluno.getBairro());
            stmt.setString(8, aluno.getCurso());
            stmt.setInt(9, aluno.getSemestre());
            stmt.setString(10, aluno.getTurno());
            stmt.setString(11, aluno.getInstituicao());
            stmt.setString(12, aluno.getTelefone());
            stmt.setString(13, aluno.getEmail());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERRO ao salvar aluno: " + e.getMessage());
        }
    }

   public Aluno buscarAluno(String nome, String senha) {
        Aluno aluno = null;
        try (Connection conn = db.getConnection()){
            String sql = "SELECT u.nome, u.senha, a.* " +
                        "FROM users u " +
                        "JOIN alunos a ON u.id = a.user_id " +
                        "WHERE u.nome = ? AND u.senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno = new Aluno(
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getString("nome_aluno"),
                    rs.getString("sobrenome_aluno"),
                    rs.getString("cpf"),
                    rs.getString("cep"),
                    rs.getString("endereco"),
                    rs.getString("bairro"),
                    rs.getString("curso"),
                    rs.getInt("semestre"),
                    rs.getString("turno"),
                    rs.getString("instituicao"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }

        } catch (Exception e) {
            System.out.println("ERRO ao buscar aluno: " + e.getMessage());
        }
        return aluno;
    }

    public Aluno pesquisarAlunoPorCpf(String cpf) {
        Aluno aluno = null;
        try (Connection conn = db.getConnection()){
            String sql = """
                SELECT a.*, u.nome, u.senha
                FROM alunos a
                JOIN users u ON a.id = u.id
                WHERE a.cpf = ?;
                """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno = new Aluno(
                        rs.getString("nome"), // do users
                        rs.getString("senha"),        // do users
                        rs.getString("nome_aluno"),
                        rs.getString("sobrenome_aluno"),
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
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = db.getConnection()){
            String sql = "SELECT u.nome, u.senha, a.* FROM users u " +
                        "JOIN alunos a ON u.id = a.user_id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getString("nome_aluno"),
                    rs.getString("sobrenome_aluno"),
                    rs.getString("cpf"),
                    rs.getString("cep"),
                    rs.getString("endereco"),
                    rs.getString("bairro"),
                    rs.getString("curso"),
                    rs.getInt("semestre"),
                    rs.getString("turno"),
                    rs.getString("instituicao"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                alunos.add(aluno);
            }

        } catch (Exception e) {
            System.out.println("ERRO ao consultar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public boolean atualizarPresencaAluno(String cpf, boolean vaiParaAula) {
        try (Connection conn = db.getConnection()){
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
            try (Connection conn = db.getConnection()){
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.println("ERRO ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
