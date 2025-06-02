package com.project.ES.Menu.MenusCadastro;

import com.project.ES.Menu.MenuBase;
import com.project.entity.Admin;
import com.project.entity.Usuario;

public class MenuCadastroAdm extends MenuBase {
    MenuCadastroMotorista validacao = new MenuCadastroMotorista();

    public Admin cadastrarAdm(){
        limparConsole();
        System.out.println("----- Adiministrador -----\n");

        MenuCadastro  cadastroadministrador = new MenuCadastro();
        Usuario usuario = cadastroadministrador.inicioCadastro();
        String nome = usuario.getNome();
        String senha = usuario.getSenha();

        String email = validacao.lerComValidacao("Email: ", 5, 100);
        
        return new Admin(nome, senha, email);

    }
}