package com.project.ES.Menu;
import com.project.ES.ES;

public class MenuPrincipal extends MenuBase{
    public void menu(){
        boolean continuar = false;
        ES es = new ES();
        
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
                    cadastro.inicioCadastro();
                    continuar=true;
                    break;
                case 2:
                    System.out.println("2");
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
