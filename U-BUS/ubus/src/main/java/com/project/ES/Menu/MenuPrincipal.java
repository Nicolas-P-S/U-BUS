package com.project.ES.Menu;

import com.project.ES.ES;
import com.project.DB.DataBaseManager;
import com.project.entity.Admin;
import com.project.entity.Aluno;
import com.project.entity.Motorista;
import com.project.entity.Usuario;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal extends MenuBase {
    private Scanner scanner;

    public void menu() {
        scanner = new Scanner(System.in);
        boolean continuar = false;
        ES es = new ES();
        Usuario usuarioLogado = null;

        while (!continuar) {
            limparConsole();
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║           Seja Bem-Vindo ao U-BUS!         ║");
            System.out.println("╠════════════════════════════════════════════╣");

            if (usuarioLogado != null) {
                System.out.printf("║            Usuario logado: %-16s║%n", usuarioLogado.getNome());
            } else {
                System.out.println("║            Nenhum usuario logado           ║");
            }
            System.out.println("╠════════════════════════════════════════════╣");

            if (usuarioLogado == null) {
                System.out.println("║ [1] -> Cadastrar-se                        ║");
                System.out.println("║ [2] -> Logar                               ║");
                System.out.println("║ [3] -> Sair                                ║");
                System.out.println("╚════════════════════════════════════════════╝");
                System.out.print("Selecione uma opção (1-3): ");

                switch (es.entradaInt(1, 3)) {
                    case 1 -> {
                        MenuCadastro cadastro = new MenuCadastro();
                        Usuario usuario = cadastro.inicioCadastro();
                        DataBaseManager db = new DataBaseManager("postgres", "1234");
                        db.conectar();
                        db.salvarUsuario(usuario);
                        System.out.println("\nUsuario cadastrado com sucesso!");
                        pausarConsole();
                    }
                    case 2 -> {
                        MenuLogin login = new MenuLogin();
                        usuarioLogado = login.inicoLogin();
                        if (usuarioLogado != null) {
                            System.out.println("\nLogin realizado com sucesso!");
                            if (usuarioLogado instanceof Aluno) {
                                new MenuPresenca().menuAluno((Aluno) usuarioLogado);
                            }
                        } else {
                            System.out.println("\nFalha no login. Tente novamente.");
                        }
                        pausarConsole();
                    }
                    case 3 -> {
                        System.out.println("\nAté a próxima!");
                        continuar = true;
                        pausarConsole();
                    }
                }
            } else {
                System.out.println("║ [1] -> Cadastrar Motorista                 ║");
                System.out.println("║ [2] -> Consultar Motoristas                ║");
                System.out.println("║ [3] -> Cadastrar Administrador             ║");
                System.out.println("║ [4] -> Cadastrar Aluno                     ║");
                System.out.println("║ [5] -> Consultar Alunos                    ║");
                System.out.println("║ [6] -> Gerenciar Minha Presença            ║");
                System.out.println("║ [7] -> Ver Lista de Presença               ║");
                System.out.println("║ [8] -> Logout                              ║");
                System.out.println("║ [9] -> Sair                                ║");
                System.out.println("╚════════════════════════════════════════════╝");
                System.out.print("Selecione uma opção (1-9): ");

                switch (es.entradaInt(1, 9)) {
                    case 1 -> {
                        if (usuarioLogado instanceof Admin) {
                            MenuCadastroMotorista menuMotorista = new MenuCadastroMotorista();
                            Motorista motorista = menuMotorista.cadastrarMotorista();
                            DataBaseManager db = new DataBaseManager("postgres", "1234");
                            db.conectar();
                            db.salvarMotorista(motorista);
                            System.out.println("\nMotorista cadastrado com sucesso!");
                        } else {
                            System.out.println("\nApenas administradores podem cadastrar motoristas.");
                        }
                        pausarConsole();
                    }
                    case 2 -> {
                        if (usuarioLogado instanceof Admin) {
                            DataBaseManager db = new DataBaseManager("postgres", "1234");
                            db.conectar();
                            List<Motorista> motoristas = db.consultarMotoristas();
                            System.out.println("\n--- Lista de Motoristas ---\n");
                            if (motoristas.isEmpty()) {
                                System.out.println("Nenhum motorista cadastrado.");
                            } else {
                                motoristas.forEach(System.out::println);
                            }
                        } else {
                            System.out.println("\nApenas administradores podem consultar motoristas.");
                        }
                        pausarConsole();
                    }
                    case 3 -> {
                        MenuCadastroAdm menuCadastroAdm = new MenuCadastroAdm();
                        Admin admin = menuCadastroAdm.cadastrarAdm();
                        DataBaseManager db = new DataBaseManager("postgres", "1234");
                        db.conectar();
                        db.salvarAdmin(admin);
                        System.out.println("\nAdministrador cadastrado com sucesso!");
                        pausarConsole();
                    }
                    case 4 -> {
                        MenuCadastroAluno menuAluno = new MenuCadastroAluno();
                        Aluno aluno = menuAluno.cadastrarAluno();
                        DataBaseManager db = new DataBaseManager("postgres", "1234");
                        db.conectar();
                        db.salvarAluno(aluno);
                        System.out.println("\nAluno cadastrado com sucesso!");
                        pausarConsole();
                    }
                    case 5 -> {
                        if (usuarioLogado instanceof Admin) {
                            DataBaseManager db = new DataBaseManager("postgres", "1234");
                            db.conectar();
                            List<Aluno> alunos = db.consultarAlunos();
                            System.out.println("\n--- Lista de Alunos ---\n");
                            if (alunos.isEmpty()) {
                                System.out.println("Nenhum aluno cadastrado.");
                            } else {
                                alunos.forEach(System.out::println);
                            }
                        } else {
                            System.out.println("\nApenas administradores podem consultar alunos.");
                        }
                        pausarConsole();
                    }
                    case 6 -> {
                        if (usuarioLogado instanceof Aluno) {
                            new MenuPresenca().menuAluno((Aluno) usuarioLogado);
                        } else {
                            System.out.println("\nEsta opção é apenas para alunos.");
                        }
                        pausarConsole();
                    }
                    case 7 -> {
                        if (usuarioLogado instanceof Admin) {
                            new MenuPresenca().verListaPresenca(usuarioLogado);
                        } else {
                            System.out.println("\nApenas administradores podem ver a lista de presença.");
                        }
                        pausarConsole();
                    }
                    case 8 -> {
                        usuarioLogado = null;
                        System.out.println("\nLogout realizado com sucesso.");
                        pausarConsole();
                    }
                    case 9 -> {
                        System.out.println("\nAté a próxima!");
                        continuar = true;
                        pausarConsole();
                    }
                }
            }
        }
    }
}
