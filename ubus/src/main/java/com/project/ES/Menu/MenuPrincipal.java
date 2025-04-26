package com.project.ES.Menu;

import com.project.ES.ES;
import com.project.DB.DataBaseManager;
import com.project.entity.Admin;
import com.project.entity.Motorista;
import com.project.entity.Usuario;

import java.util.List;

public class MenuPrincipal extends MenuBase {
    public void menu() {
        boolean continuar = false;
        ES es = new ES();
        Usuario usuarioLogado = null;

        while (!continuar) {
            System.out.println("""
                --------- Seja Bem-Vindo ao U-BUS! ---------

                [1]: Cadastrar-se
                [2]: Logar
                [3]: Cadastrar Motorista
                [4]: Consultar Motoristas
                [5]: Cadastrar Administrador
                [6]: Sair
                """);

            switch (es.entradaInt(1, 6)) {
                case 1 -> {
                    MenuCadatro cadastro = new MenuCadatro();
                    Usuario usuario = cadastro.inicioCadastro();
                    DataBaseManager db = new DataBaseManager("", "");
                    db.conectar();
                    db.salvarUsuario(usuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    pausarConsole();
                    limparConsole();
                }
                case 2 -> {
                    MenuLogin login = new MenuLogin();
                    usuarioLogado = login.inicoLogin();
                    System.out.println("Login realizado com sucesso!");
                    pausarConsole();
                    limparConsole();
                }
                case 3 -> {
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar logado para cadastrar um motorista.");
                    } else {
                        if (usuarioLogado instanceof Admin) {
                            MenuCadastroMotorista menuMotorista = new MenuCadastroMotorista();
                            Motorista motorista = menuMotorista.cadastrarMotorista();
                            DataBaseManager db = new DataBaseManager("login", "senha");
                            db.conectar();
                            db.salvarMotorista(motorista);
                            System.out.println("Motorista cadastrado com sucesso!");
                            }else{
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
                    limparConsole();
                    System.out.println("Até a próxima!");
                    pausarConsole();
                    limparConsole();
                    continuar = true;
                }
            }
        }
    }
}
