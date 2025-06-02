package com.project.ES.Menu;

import com.project.entity.Usuario;
import com.project.DB.DataBaseManager;

public class MenuCadastro extends MenuBase {
    DataBaseManager db = new DataBaseManager("postgres", "1234");

    public void logoCadastro() {
        limparConsole();
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║         MENU DE CADASTRO         ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println();
    }

    public String criarUser() {
        boolean continuar = false;
        String resposta = "";

        while (!continuar) {
            System.out.println("╔════════════════════════╗");
            System.out.println("║ (1/2) Crie um usuario: ║");
            System.out.println("╚════════════════════════╝");
            resposta = this.es.entradaString();

            if ((resposta.length() >= 4 && resposta.length() <= 20) && (db.pesquisarUsuarioNome(resposta) == null)) {
                continuar = true;
            } else {
                limparConsole();
                System.out.println("╔══════════════════════════════════════╗");
                if (resposta.length() < 4 || resposta.length() > 20) {
                    System.out.println("║ ERRO: Digite um usuario entre 4 a 20 ║");
                    System.out.println("║ caracteres.                          ║");
                } else {
                    System.out.println("║ ERRO: Usuario ja cadastrado!         ║");
                }
                System.out.println("╚══════════════════════════════════════╝");
                pausarConsole();
                limparConsole();
            }
        }
        return resposta;
    }

    public String criarPass() {
        boolean continuar = false;
        String resposta = "";

        while (!continuar) {
            System.out.println("╔═══════════════════════╗");
            System.out.println("║ (2/2) Crie uma senha: ║");
            System.out.println("╚═══════════════════════╝");
            resposta = this.es.entradaString();

            if (resposta.length() >= 8 && resposta.length() <= 20) {
                continuar = true;
            } else {
                limparConsole();
                System.out.println("╔══════════════════════════════════════╗");
                System.out.println("║ ERRO: Digite uma senha entre 8 a 20  ║");
                System.out.println("║ caracteres.                          ║");
                System.out.println("╚══════════════════════════════════════╝");
                pausarConsole();
                limparConsole();
            }
        }
        return resposta;
    }

    public Usuario inicioCadastro() {
        logoCadastro();
        String user = criarUser();
        logoCadastro();
        String password = criarPass();
        Usuario usuario = new Usuario(user, password);

        System.out.println();
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║ Cadastro realizado com sucesso! ║");
        System.out.println("╚═════════════════════════════════╝");

        return usuario;
    }
}
