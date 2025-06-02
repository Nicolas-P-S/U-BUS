package com.project.ES.Menu;

import com.project.ES.ES;
import com.project.entity.Admin;
import com.project.entity.Usuario;

public class MenuCadastroAdm extends MenuBase {
    ES es = new ES();
    MenuCadastroMotorista validacao = new MenuCadastroMotorista();

    public Admin cadastrarAdm() {
        limparConsole();
        System.out.println("╔═════════════════════════╗");
        System.out.println("║      ADMINISTRADOR      ║");
        System.out.println("╚═════════════════════════╝");
        System.out.println();

        MenuCadastro cadastroadministrador = new MenuCadastro();
        Usuario usuario = cadastroadministrador.inicioCadastro();
        String nome = usuario.getNome();
        String senha = usuario.getSenha();

        System.out.println("╔════════╗");
        System.out.println("║ Email: ║");
        System.out.println("╚════════╝");
        String email = validacao.lerComValidacao(" ", 5, 100);

        System.out.println("╔═══════╗");
        System.out.println("║ Tipo: ║");
        System.out.println("╚═══════╝");
        String tipo = validacao.lerComValidacao(" ", 3, 50);

        System.out.println();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║ Administrador cadastrado com ║");
        System.out.println("║ sucesso!                     ║");
        System.out.println("╚══════════════════════════════╝");

        return new Admin(nome, senha, email, tipo);
    }
}
