package com.project.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.project.entity.Instituicao;

public class InstituicaoDBG extends UserDBG{

    public InstituicaoDBG(DataBaseManager db){
        super(db);
    }

    public void salvarInstituicao(String nomeInstituicao) {
        String sql = "INSERT INTO instituicao (" +
                    "nome) " +
                    "VALUES (?)";
        try (
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, nomeInstituicao);
            stmt.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Instituicao buscarInstituicao(String nome) {
        Instituicao instituicao=null;
        try (Connection conn = db.getConnection()){
            String sql = "SELECT * " +
                        "FROM instituicao " +
                        "WHERE instituicao.nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                instituicao = new Instituicao(rs.getString("nome"), rs.getInt("id"));
            }

        } catch (Exception e) {
            System.out.println("ERRO ao buscar Instituicao: " + e.getMessage());
        }
        return instituicao;
    }

    public Instituicao buscarInstituicao(int id) {
        Instituicao instituicao=null;
        try (Connection conn = db.getConnection()){
            String sql = "SELECT *" +
                        "FROM instituicao " +
                        "WHERE instituicao.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                instituicao = new Instituicao(rs.getString("nome"), rs.getInt("id"));
            }

        } catch (Exception e) {
            System.out.println("ERRO ao buscar Instituicao: " + e.getMessage());
        }
        return instituicao;
    }
}
