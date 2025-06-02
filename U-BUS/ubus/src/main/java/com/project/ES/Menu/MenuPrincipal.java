package com.project.ES.Menu;

import com.project.ES.ES;
import com.project.ES.Menu.MenusCadastro.MenuCadastroAdm;
import com.project.ES.Menu.MenusCadastro.MenuCadastroAluno;
import com.project.ES.Menu.MenusCadastro.MenuCadastroMotorista;
import com.project.entity.Admin;
import com.project.entity.Aluno;
import com.project.entity.Motorista;
import com.project.entity.Usuario;
import java.util.List;

public class MenuPrincipal extends MenuBase {
    public void menu() {
        db.conectar();
        db.criarBanco();
        db.criarTabelas();

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
                        MenuCadastroAdm cadastro = new MenuCadastroAdm();
                        Admin usuario = cadastro.cadastrarAdm();
                        
                        db.salvarAdmin(usuario);
                        System.out.println("\nUsuario cadastrado com sucesso!");
                        pausarConsole();
                    }
                    case 2 -> {
                        MenuLogin login = new MenuLogin();
                        usuarioLogado = login.inicoLogin();
                        if (usuarioLogado != null) {
                            System.out.println("\nLogin realizado com sucesso!");
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
                System.out.println("║ [3] -> Cadastrar Aluno                     ║");
                System.out.println("║ [4] -> Consultar Alunos                    ║");
                System.out.println("║ [5] -> Ver Lista de Presença               ║");
                System.out.println("║ [6] -> Gerar Lista de Presença             ║");
                System.out.println("║ [7] -> Gerar Alunos Automaticamente        ║");   
                System.out.println("║ [8] -> Logout                              ║");
                System.out.println("║ [9] -> Sair                                ║");
                System.out.println("╚════════════════════════════════════════════╝");
                System.out.print("Selecione uma opção (1-9): ");

                switch (es.entradaInt(1, 9)) {
                    case 1 -> {
                        if (usuarioLogado instanceof Admin) {
                            MenuCadastroMotorista menuMotorista = new MenuCadastroMotorista();
                            Motorista motorista = menuMotorista.cadastrarMotorista();
                            
                            db.salvarMotorista(motorista);
                            System.out.println("\nMotorista cadastrado com sucesso!");
                        } else {
                            System.out.println("\nApenas administradores podem cadastrar motoristas.");
                        }
                        pausarConsole();
                    }
                    case 2 -> {
                        if (usuarioLogado instanceof Admin) {
                            
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
                        MenuCadastroAluno menuAluno = new MenuCadastroAluno();
                        Aluno aluno = menuAluno.cadastrarAluno();
                        
                        db.salvarAluno(aluno);
                        System.out.println("\nAluno cadastrado com sucesso!");
                        pausarConsole();
                    }
                    case 4 -> {
                        if (usuarioLogado instanceof Admin) {
                            
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
                    case 5 -> {
                        if (usuarioLogado instanceof Admin) {
                            // ver LISTA
                        } else {
                            System.out.println("\nApenas administradores podem ver a lista de presença.");
                        }
                        pausarConsole();
                    }
                    case 6 -> {
                        
                    }
                    case 7 -> {
                        if (usuarioLogado instanceof Admin) {
                            new MenuGerarAlunos().iniciarGeracaoAlunos();
                        } else {
                            System.out.println("\nApenas administradores gerar alunos.");
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