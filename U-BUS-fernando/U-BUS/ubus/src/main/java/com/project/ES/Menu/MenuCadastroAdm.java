package com.project.ES.Menu;

import com.project.ES.ES;
import com.project.entity.Admin;
import com.project.entity.Usuario;

public class MenuCadastroAdm extends MenuBase {
    ES es = new ES();
    MenuCadastroMotorista validacao = new MenuCadastroMotorista();

    public Admin cadastrarAdm(){
        limparConsole();
        System.out.println("----- Adiministrador -----\n");

        MenuCadatro  cadastroadministrador = new MenuCadatro();
        Usuario usuario = cadastroadministrador.inicioCadastro();
        String nome = usuario.getNome();
        String senha = usuario.getSenha();

        String email = validacao.lerComValidacao("Email: ", 5, 100);
        String tipo =  validacao.lerComValidacao("Tipo: ", 3, 50);

        return new Admin(nome, senha, email, tipo);

    }
}
