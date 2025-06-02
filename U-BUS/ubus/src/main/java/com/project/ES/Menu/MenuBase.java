package com.project.ES.Menu;

import com.project.ES.ES;
import com.project.DB.DataBaseManager;

public class MenuBase {

    public DataBaseManager db = new DataBaseManager("postgres", "1234");
    public ES es = new ES();

    public void limparConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║   ERRO: Nao foi possivel limpar o terminal!   ║");
            System.out.println("╚══════════════════════════════════════════╝\n");
        }
    }

    public void pausarConsole() {
        System.out.println("\nPressione ENTER para continuar...");
        es.entradaString();
    }
}
