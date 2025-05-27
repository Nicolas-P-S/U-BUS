package com.project.ES.Menu;

import com.project.DB.DataBaseManager;
import com.project.entity.Admin;
import com.project.entity.Aluno;
import com.project.entity.Usuario;
import com.project.ES.ES;

public class MenuLogin extends MenuBase {
    private DataBaseManager db = new DataBaseManager("", "");
    private ES es = new ES();
    
    public void logoLogin() {
        limparConsole();
        System.out.println("""
            --------- Entre com a sua conta! ---------
            """);
    }

    public Usuario inicoLogin() {
        logoLogin();
        
        // Primeiro obtém o nome de usuário
        System.out.print("(1/2) Usuário: ");
        String username = es.entradaString();
        
        // Verifica se o usuário existe
        Usuario usuario = db.pesquisarUsuarioNome(username);
        if (usuario == null) {
            System.out.println("ERRO: Usuário não cadastrado!");
            pausarConsole();
            return null;
        }

        logoLogin();
        System.out.print("(2/2) Senha: ");
        String password = es.entradaString();

        // Verifica primeiro se é admin
        Admin admin = db.buscarAdmin(username, password);
        if (admin != null) {
            System.out.println("Login como administrador realizado com sucesso!");
            return admin;
        }

        // Verifica se é aluno
        Aluno aluno = db.buscarAluno(username, password);
        if (aluno != null) {
            System.out.println("Login como aluno realizado com sucesso!");
            return aluno;
        }

        // Verifica usuário comum
        usuario = db.pesquisarUsuarioNome(username);
        if (usuario != null && usuario.getSenha().equals(password)) {
            System.out.println("Login como usuário comum realizado com sucesso!");
            return usuario;
        }

        System.out.println("ERRO: Senha incorreta!");
        pausarConsole();
        return null;
    }
}