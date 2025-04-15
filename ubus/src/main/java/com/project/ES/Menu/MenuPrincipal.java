package com.project.ES.Menu;
import com.project.ES.ES;

public class MenuPrincipal {
    public static void menu(){
        ES es = new ES();

        System.out.println("""
---------Seja Bem Vindo ao U-BUS!---------


[1]: Cadastrar-se
[2]: Logar
[3]: Sair
        """);

        switch (es.entradaInt(1, 3)) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
        }
    }
}
