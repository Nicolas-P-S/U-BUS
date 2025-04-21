package com.project.ES.Menu;
import com.project.ES.ES;
import com.project.entity.Usuario;
import com.project.DB.DataBaseManager;

public class MenuPrincipal extends MenuBase{
    public void menu(){
        boolean continuar = false;
        ES es = new ES();
        DataBaseManager db = new DataBaseManager("censurado", "censurado");
        db.conectar();
        db.criarBanco();
        Usuario usuario = null;
        
        while (!continuar){
            System.out.println("""
    ---------Seja Bem Vindo ao U-BUS!---------
    
    
    [1]: Cadastrar-se
    [2]: Logar
    [3]: Sair
            """);
    
            switch (es.entradaInt(1, 3)) {
                case 1:
                    MenuCadatro cadastro = new MenuCadatro();
                    usuario = cadastro.inicioCadastro();
                    db.salvarUsuario(usuario);
                    continuar=true;
                    break;
                case 2:
                    MenuLogin login = new MenuLogin();
                    usuario = login.inicoLogin();
                    continuar=true;
                    break;
                case 3:
                    limparConsole();
                    System.out.println("Até a proxima!");
                    pausarConsole();
                    limparConsole();
                    continuar=true;
                    break;
            }
            
        }
    }
}
