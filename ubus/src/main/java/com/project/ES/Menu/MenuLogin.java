package com.project.ES.Menu;
import com.project.DB.DataBaseManager;
import com.project.entity.Usuario;

public class MenuLogin extends MenuBase{
    DataBaseManager db = new DataBaseManager("censurado", "censurado");
    
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

            usuario = db.pesquisarUsuarioNome(resposta);
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

            usuario = db.pesquisarUsuarioSenha(resposta);
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

    public Usuario inicoLogin(){
        logoLogin();
        String name = inserirUser();
        logoLogin();
        String password = inserirPass();
        
        Usuario usuario = new Usuario(name, password);
        return usuario;
    }
}
