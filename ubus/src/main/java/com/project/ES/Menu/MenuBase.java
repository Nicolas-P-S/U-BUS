package com.project.ES.Menu;
import com.project.ES.ES;

public class MenuBase {
    ES es = new ES();
    public void limparConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("ERRO: não foi possivel limpar o terminal!");
        }
    }

    public void pausarConsole(){
        System.out.println("Digite qualquer tecla para continuar!");
        es.entradaString();
    }
}
