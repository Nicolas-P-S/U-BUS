package com.project.ES.Menu;

import java.util.List;
import java.util.Scanner;

import com.project.DB.DataBaseManager;
import com.project.ES.ES;
import com.project.entity.Admin;
import com.project.entity.Aluno;
import com.project.entity.Motorista;
import com.project.entity.Usuario;

public class MenuAdm extends MenuBase {
    private Scanner scan = new Scanner(System.in);

    public void LogoMenu() {
        System.out.println("""
                --------- Seja Bem-Vindo ao U-BUS! ---------

                [1]: Cadastrar Motorista
                [2]: Consultar Motoristas
                [3]: Cadastrar Administrador
                [4]: Cadastrar Aluno
                [5]: Consultar Alunos 
                [6]: Ver Lista de Presença 
                [7]: Sair
                """);
    }

    public void Menu(Admin adm) {
        boolean continuar = false;
        ES es = new ES();
        Usuario usuarioLogado = null;

        while (!continuar) {
            LogoMenu();

            switch (es.entradaInt(1, 7)) {
                case 1:
                    MenuCadastroMotorista menuMotorista = new MenuCadastroMotorista();
                    Motorista motorista = menuMotorista.cadastrarMotorista();
                    DataBaseManager db = new DataBaseManager("posgres", "password");
                    db.conectar();
                    db.salvarMotorista(motorista);
                    System.out.println("Motorista cadastrado com sucesso!");
                    pausarConsole();
                    limparConsole();
                    break;

                case 2:
                    DataBaseManager banco = new DataBaseManager("", "");
                    banco.conectar();
                    List<Motorista> motoristas = banco.consultarMotoristas();

                    if (motoristas.isEmpty()) {
                        System.out.println("Nenhum motorista cadastrado.");
                    } else {
                        System.out.println("--- Lista de Motoristas ---\n");
                        for (Motorista mot : motoristas) {
                            System.out.println(mot);
                        }
                    }
                    pausarConsole();
                    limparConsole();
                    break;

                case 3:
                    MenuCadastroAdm menuCadastroAdm = new MenuCadastroAdm();
                    Admin admin = menuCadastroAdm.cadastrarAdm();
                    DataBaseManager bd = new DataBaseManager("postgres", "password");
                    bd.conectar(); 
                    bd.salvarAdmin(admin); 
                    pausarConsole();
                    limparConsole();
                    break;

                case 4:
                    MenuCadastroAluno menuAluno = new MenuCadastroAluno();
                    Aluno aluno = menuAluno.cadastrarAluno();
                    DataBaseManager bancoD = new DataBaseManager("postgres", "password");
                    bancoD.conectar();
                    bancoD.salvarAluno(aluno);
                    System.out.println("Aluno cadastrado com sucesso!");
                    pausarConsole();
                    limparConsole();
                    break;

                case 5:
                    DataBaseManager dados = new DataBaseManager("postgres", "password");
                    dados.conectar();
                    List<Aluno> alunos = dados.consultarAlunos();

                        if (alunos.isEmpty()) {
                            System.out.println("Nenhum aluno cadastrado.");
                        } else {
                            System.out.println("--- Lista de Alunos ---\n");
                            for (Aluno alun : alunos) {
                                System.out.println(alun);
                            }
                        }
                    
                    pausarConsole();
                    limparConsole();
                    break;

                case 6:
                    new MenuPresenca().verListaPresenca(usuarioLogado);
                    pausarConsole();
                    limparConsole();
                    break;

                case 7:
                    continuar = true;
                    break;
            }
        }
    }
}
