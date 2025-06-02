package com.project.ES.Menu.MenusCadastro;

import com.project.ES.Menu.MenuBase;
import com.project.entity.Motorista;
import com.project.entity.Usuario;

public class MenuCadastroMotorista extends MenuBase {

    public Motorista cadastrarMotorista() {
        limparConsole();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║     MENU DE CADASTRO DE MOTORISTA     ║");
        System.out.println("╚═══════════════════════════════════════╝\n");

        MenuCadastro cadastroBasico = new MenuCadastro();
        Usuario usuario = cadastroBasico.inicioCadastro();
        String nome = usuario.getNome();
        String senha = usuario.getSenha();

        limparConsole();
        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║  PREENCHA OS DADOS ABAIXO:  ║");
        System.out.println("╚═════════════════════════════╝\n");

        String cpf = lerComValidacao("CPF (somente numeros)", 11, 11);
        String endereco = lerComValidacao("Endereco", 1, 100);
        String cnh = lerComValidacao("CNH", 9, 9);
        String categoriaCNH = lerComValidacao("Categoria da CNH", 1, 3);
        String email = lerComValidacao("Email", 5, 100);
        String telefone = lerComValidacao("Telefone (com DDD)", 10, 11);

        return new Motorista(nome, senha, cpf, cnh, categoriaCNH, telefone, email, endereco);
    }

    String lerComValidacao(String campo, int min, int max) {
    String input = "";
    boolean valido = false;

    while (!valido) {
        limparConsole();

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║  PREENCHA OS DADOS ABAIXO:  ║");
        System.out.println("╚═════════════════════════════╝\n");

        System.out.print(campo + ": ");
        input = es.entradaString().trim();

        if (input.length() < min || input.length() > max) {
            mostrarErro("O campo \"" + campo + "\" deve ter entre " + min + " e " + max + " caracteres.");
            continue;
        }

        switch (campo.toLowerCase()) {
            case "cpf (somente numeros)":
                if (!input.matches("\\d{11}")) {
                    mostrarErro("CPF deve conter exatamente 11 numeros.");
                    continue;
                }
                break;
            case "cnh":
                if (!input.matches("\\d{9}")) {
                    mostrarErro("CNH deve conter exatamente 9 numeros.");
                    continue;
                }
                break;
            case "categoria da cnh":
                if (input.length() > 3) {
                    mostrarErro("Categoria da CNH deve ter no maximo 3 caracteres.");
                    continue;
                }
                break;
            case "telefone (com ddd)":
            case "telefone":
                if (!input.matches("\\d{10,11}")) {
                    mostrarErro("Telefone deve conter entre 10 e 11 digitos numericos.");
                    continue;
                }
                break;
            case "email":
                if (!input.contains("@") || !input.contains(".")) {
                    mostrarErro("E-mail invalido. Verifique o formato (ex: exemplo@email.com).");
                    continue;
                }
                break;
        }

        valido = true;
    }

    return input;
}


    private void mostrarErro(String mensagem) {
        System.out.printf ("ERRO: %-34s%n", mensagem);
        pausarConsole();
    }
}
