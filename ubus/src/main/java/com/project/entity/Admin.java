package com.project.entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Usuario {
    private String email;
    private String tipo;


    public Admin(String nome, String senha, String email, String tipo) {
        super(nome, senha);
        this.email = email;
        this.tipo = tipo;
    }

    public Admin() {
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Admin [email=" + email + ", tipo=" + tipo + "]";
    }


    private List listadeAlunosInstituicao(String instituição){
        String url = "jdbc:postgresql://localhost:5432/teste";
        String user = "postgres";
        String senha = "password";
        List<String> alunos = new ArrayList<>();

        try {
            Connection conexao = DriverManager.getConnection(url,user,senha);
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nome FROM alunos WHERE instituicao ="+instituição);

            while(rs.next()){
                String nome = rs.getString("nome");
                alunos.add(nome);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alunos;

        


    }

    public void visualizarLista(String instituicao){
        List <String> lista = listadeAlunosInstituicao(instituicao);
        System.out.println("LISTA DE ALUNOS DA "+ instituicao.toUpperCase());
        for(String a:lista){
            System.out.println("- "+ a);
        }
        
    }



    
    
}
