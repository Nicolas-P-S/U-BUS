package com.project.ES.Menu;

import java.util.Scanner;

import com.project.DB.DataBaseManager;
import com.project.ES.ES;
import com.project.entity.Admin;
import com.project.entity.Aluno;
import com.project.entity.Usuario;

public class MenuPrincipal extends MenuBase {
    private Scanner scanner;
    public void menu() {
        scanner = new Scanner(System.in);
        boolean continuar = false;
        ES es = new ES();
        Usuario usuarioLogado = null;
        

        while (!continuar) {
            System.out.println("""
                --------- Seja Bem-Vindo ao U-BUS! ---------

                [1]: Cadastrar-se
                [2]: Logar

                """);

            switch (es.entradaInt(1, 10)) {  
                case 1 -> {
                    MenuCadatro cadastro = new MenuCadatro();
                    Usuario usuario = cadastro.inicioCadastro();
                    DataBaseManager db = new DataBaseManager("postgres", "password");
                    db.conectar();
                    db.salvarUsuario(usuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    pausarConsole();
                    limparConsole();
                }
                case 2 -> {
                    MenuLogin login = new MenuLogin();
                    usuarioLogado = login.inicoLogin();
                    if (usuarioLogado != null) {
                        System.out.println("Login realizado com sucesso!");
                        
                        if (usuarioLogado instanceof Aluno) {
                            MenuPresenca menuPresenca = new MenuPresenca();
                            menuPresenca.menuAluno((Aluno) usuarioLogado);
                        }
                        else if(usuarioLogado instanceof Admin){
                            MenuAdm menuAdm = new MenuAdm();
                            menuAdm.Menu((Admin) usuarioLogado);
                            
                        }
                    }
                    pausarConsole();
                    limparConsole();
                }

    /*            
                case 3 -> {
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar logado para cadastrar um motorista.");
                    } else {
                        if (usuarioLogado instanceof Admin) {
                            MenuCadastroMotorista menuMotorista = new MenuCadastroMotorista();
                            Motorista motorista = menuMotorista.cadastrarMotorista();
                            DataBaseManager db = new DataBaseManager("", "");
                            db.conectar();
                            db.salvarMotorista(motorista);
                            System.out.println("Motorista cadastrado com sucesso!");
                        } else {
                            System.out.println("Somente administradores podem cadastrar motoristas.");
                        }
                    }
                    pausarConsole();
                    limparConsole();
                }
                case 4 -> {
                    if (usuarioLogado instanceof Admin) {
                        DataBaseManager db = new DataBaseManager("", "");
                        db.conectar();
                        List<Motorista> motoristas = db.consultarMotoristas();

                        if (motoristas.isEmpty()) {
                            System.out.println("Nenhum motorista cadastrado.");
                        } else {
                            System.out.println("--- Lista de Motoristas ---\n");
                            for (Motorista mot : motoristas) {
                                System.out.println(mot);
                            }
                        }
                    } else {
                        System.out.println("Somente administradores podem consultar motoristas.");
                    }
                    pausarConsole();
                    limparConsole();
                }
                case 5 -> {
                    MenuCadastroAdm menuCadastroAdm = new MenuCadastroAdm();
                    Admin admin = menuCadastroAdm.cadastrarAdm();
                    DataBaseManager db = new DataBaseManager("", "");
                    db.conectar();
                    db.salvarAdmin(admin);
                    System.out.println("Administrador cadastrado com sucesso!");
                    pausarConsole();
                    limparConsole();
                }
                case 6 -> {
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar logado para cadastrar um aluno.");
                    } else {
                        MenuCadastroAluno menuAluno = new MenuCadastroAluno();
                        Aluno aluno = menuAluno.cadastrarAluno();
                        DataBaseManager db = new DataBaseManager("", "");
                        db.conectar();
                        db.salvarAluno(aluno);
                        System.out.println("Aluno cadastrado com sucesso!");
                    }
                    pausarConsole();
                    limparConsole();
                }
                case 7 -> {
                    if (usuarioLogado instanceof Admin) {
                        DataBaseManager db = new DataBaseManager("", "");
                        db.conectar();
                        List<Aluno> alunos = db.consultarAlunos();

                        if (alunos.isEmpty()) {
                            System.out.println("Nenhum aluno cadastrado.");
                        } else {
                            System.out.println("--- Lista de Alunos ---\n");
                            for (Aluno aluno : alunos) {
                                System.out.println(aluno);
                            }
                        }
                    } else {
                        System.out.println("Somente administradores podem consultar alunos.");
                    }
                    pausarConsole();
                    limparConsole();
                }
                case 8 -> {
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa fazer login primeiro.");
                        System.out.print("Deseja fazer login agora? (S/N): ");
                        String resposta = scanner.nextLine();
                        if (resposta.equalsIgnoreCase("S")) {
                            usuarioLogado = new MenuLogin().inicoLogin();
                            if (usuarioLogado instanceof Aluno) {
                                new MenuPresenca().menuAluno((Aluno) usuarioLogado);
                            } else {
                                System.out.println("Esta opção é apenas para alunos.");
                            }
                        }
                    } 
                    else if (usuarioLogado instanceof Aluno) {
                        new MenuPresenca().menuAluno((Aluno) usuarioLogado);
                    } 
                    else {
                        System.out.println("Esta opção é disponível apenas para alunos.");
                    }
                    pausarConsole();
                    limparConsole();
                }
                case 9 -> {
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar logado como administrador para ver a lista de presença.");
                        System.out.print("Deseja fazer login agora? (S/N): ");
                        String resposta = scanner.nextLine();
                        if (resposta.equalsIgnoreCase("S")) {
                            usuarioLogado = new MenuLogin().inicoLogin();
                            if (usuarioLogado instanceof Admin) {
                                new MenuPresenca().verListaPresenca(usuarioLogado);
                            } else {
                                System.out.println("Somente administradores podem ver a lista de presença.");
                            }
                        }
                    } 
                    else if (usuarioLogado instanceof Admin) {
                        new MenuPresenca().verListaPresenca(usuarioLogado);
                    } 
                    else {
                        System.out.println("Somente administradores podem ver a lista de presença.");
                    }
                    pausarConsole();
                    limparConsole();
                }
                case 10 -> {
                    limparConsole();
                    System.out.println("Até a próxima!");
                    pausarConsole();
                    limparConsole();
                    continuar = true;
                }*/
            } 
        }
    }
}

