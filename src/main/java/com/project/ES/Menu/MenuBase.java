package com.project.ES.Menu;
import com.project.ES.ES;
import com.project.DB.*;

public class MenuBase {
    public DataBaseManager db = new DataBaseManager(null, null);
    public MotoristaDBG mDB = new MotoristaDBG(db);
    public UserDBG uDB = new UserDBG(db);
    public AlunoDBG alDB = new AlunoDBG(db);
    public AdminDBG adDB = new AdminDBG(db);

    public ES es = new ES();
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
