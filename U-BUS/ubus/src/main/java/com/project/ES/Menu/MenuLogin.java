package com.project.ES.Menu;

import com.project.entity.Usuario;
import com.project.entity.Admin;

public class MenuLogin extends MenuBase {    
    public void logoLogin() {
        limparConsole();
        System.out.println("╔════════════════════════╗");
        System.out.println("║      MENU DE LOGIN     ║");
        System.out.println("╚════════════════════════╝");
        System.out.println();
    }

    public String inserirUser() {
        boolean continuar = false;
        String resposta = "";
        Usuario usuario = null;

        while (!continuar) {
            System.out.println("╔═════════════════════════╗");
            System.out.println("║ (1/2) Digite o usuario: ║");
            System.out.println("╚═════════════════════════╝");
            resposta = es.entradaString();

            usuario = db.pesquisarUsuarioNome(resposta);
            if (usuario != null) {
                continuar = true;
            } else {
                limparConsole();
                System.out.println("╔═══════════════════════════════╗");
                System.out.println("║ ERRO: Usuario nao cadastrado! ║");
                System.out.println("╚═══════════════════════════════╝");
                pausarConsole();
                limparConsole();
            }
        }
        return usuario.getNome();
    }

    public String inserirPass() {
        boolean continuar = false;
        String resposta = "";
        Usuario usuario = null;

        while (!continuar) {
            System.out.println("╔═══════════════════════╗");
            System.out.println("║ (2/2) Digite a senha: ║");
            System.out.println("╚═══════════════════════╝");
            resposta = es.entradaString();

            usuario = db.pesquisarUsuarioSenha(resposta);
            if (usuario != null) {
                continuar = true;
            } else {
                limparConsole();
                System.out.println("╔════════════════════════╗");
                System.out.println("║ ERRO: Senha incorreta! ║");
                System.out.println("╚════════════════════════╝");
                pausarConsole();
                limparConsole();
            }
        }
        return usuario.getSenha();
    }

    public Admin inicioLogin() {
        logoLogin();
        String name = inserirUser();
        logoLogin();
        inserirPass();
        
        Admin usuario = db.pesquisarAdminPorNome(name);
        return usuario;
    }
}
