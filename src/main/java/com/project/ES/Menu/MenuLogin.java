package com.project.ES.Menu;
import com.project.entity.Usuario;
import com.project.entity.Admin;


public class MenuLogin extends MenuBase{    
    public void logoLogin(){
        limparConsole();
        System.out.println("""
---------Entre com a sua conta!---------

                """);
    }

    public String inserirUser(){
        boolean continuar = false;
        String resposta = "";
        Usuario usuario = null;

        while (!continuar){
            System.out.println("(1/2) Usuario: ");
            resposta = es.entradaString();

            usuario = uDB.pesquisarUsuarioNome(resposta);
            if (usuario != null)
                continuar = true;
            else{
                limparConsole();
                System.out.println("ERRO: usuario não cadastrado!");
                pausarConsole();
                limparConsole();
            }
        }
        return usuario.getNome();
    }

    public String inserirPass(){
        boolean continuar = false;
        String resposta = "";
        Usuario usuario = null;

        while (!continuar){
            System.out.println("(2/2) Senha: ");
            resposta = es.entradaString();

            usuario = uDB.pesquisarUsuarioSenha(resposta);
            if (usuario != null)
                continuar = true;
            else{
                limparConsole();
                System.out.println("ERRO: senha incorreta!");
                pausarConsole();
                limparConsole();
            }
        }
        return usuario.getSenha();
    }

    public Admin inicoLogin(){
        logoLogin();
        String name = inserirUser();
        logoLogin();
        inserirPass();
        
        Admin usuario = adDB.pesquisarAdminPorNome(name);
        return usuario;
    }
}
