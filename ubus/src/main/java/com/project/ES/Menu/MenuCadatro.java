package com.project.ES.Menu;
import com.project.entity.Usuario;
import com.project.DB.DataBaseManager;

public class MenuCadatro extends MenuBase{
    DataBaseManager db = new DataBaseManager("", "");

    public void logoCadastro(){
        limparConsole();
        System.out.println("""
---------CADASTRE-SE---------

                """);
    }

    public String criarUser(){
        boolean continuar = false;
        String resposta = "";

        while (!continuar){
            System.out.println("(1/2) Crie um usuario: ");
            resposta = this.es.entradaString();

            if ((resposta.length() >= 4 && resposta.length() <= 20) && (db.pesquisarUsuarioNome(resposta) == null))
                continuar=true;
            else{
                limparConsole();
                if (resposta.length() < 4 || resposta.length() > 20)
                    System.out.println("ERRO: digite um usuario entre 4 a 20 caracteres ou usuario!");
                else
                    System.out.println("ERRO: usuario já cadastrado!");
                pausarConsole();
                limparConsole();
            }
        }
        return resposta;
    }

    public String criarPass(){
        boolean continuar = false;
        String resposta = "";
        
        while (!continuar){
            System.out.println("(2/2) Crie uma senha: ");
            resposta = this.es.entradaString();

            if (resposta.length() >= 8 && resposta.length() <= 20) // requisito min e max de caracteres
                continuar=true; // sai do loop
            else{
                limparConsole();
                System.out.println("ERRO: digite uma senha entre 8 a 20 caracteres");
                pausarConsole();
                limparConsole();
            }
        }
        return resposta;
    }

    public Usuario inicioCadastro(){ // metodo inicial para cadastro
        logoCadastro();
        String user = criarUser();
        logoCadastro();
        String password = criarPass();
        Usuario usuario = new Usuario(user, password);
        
        return usuario;
    }
}
