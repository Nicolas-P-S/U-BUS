package com.project.ES.Menu.MenusCadastro;

import com.project.ES.Menu.MenuBase;
import com.project.entity.Aluno;
import com.project.entity.Usuario;

public class MenuCadastroAluno extends MenuBase {

    public Aluno cadastrarAluno() {
        limparConsole();
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║     MENU DE CADASTRO ALUNO     ║");
        System.out.println("╚════════════════════════════════╝\n");

        MenuCadastro cadastroBasico = new MenuCadastro();
        Usuario usuario = cadastroBasico.inicioCadastro();
        String nome = usuario.getNome();
        String senha = usuario.getSenha();

        limparConsole();
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║   PREENCHA OS DADOS ABAIXO:    ║");
        System.out.println("╚════════════════════════════════╝\n");

        String nome_aluno = lerComValidacao("Nome", 1, 45);
        String sobrenome_aluno = lerComValidacao("Sobrenome", 1, 45);
        String cpf = lerComValidacao("CPF (somente numeros)", 11, 11);
        String cep = lerComValidacao("CEP (somente numeros)", 8, 8);
        String endereco = lerComValidacao("Endereco", 5, 100);
        String bairro = lerComValidacao("Bairro", 3, 50);
        String curso = lerComValidacao("Curso", 3, 50);
        int semestre = lerSemestre();
        String turno = lerTurno();
        String instituicao = lerComValidacao("Instituicao de Ensino", 3, 100);
        String telefone = lerComValidacao("Telefone (com DDD)", 10, 11);
        String email = lerComValidacao("Email", 5, 100);

        return new Aluno(nome, senha, nome_aluno, sobrenome_aluno, cpf, cep, endereco, bairro, curso,
                semestre, turno, instituicao, telefone, email);
    }

    private int lerSemestre() {
        while (true) {
            limparConsole();
            System.out.println("Informe o semestre (1-12):");
            String input = es.entradaString().trim();

            try {
                int semestre = Integer.parseInt(input);
                if (semestre >= 1 && semestre <= 12) {
                    return semestre;
                } else {
                    mostrarErro("O semestre deve ser um numero entre 1 e 12.");
                }
            } catch (NumberFormatException e) {
                mostrarErro("Por favor, insira um número valido para o semestre.");
            }
        }
    }

    private String lerTurno() {
        while (true) {
            limparConsole();
            System.out.println("Informe o turno (Manha/Tarde/Noite):");
            String input = es.entradaString().trim().toLowerCase();

            if (input.equals("manha") || input.equals("tarde") || input.equals("noite")) {
                return input.substring(0, 1).toUpperCase() + input.substring(1);
            } else {
                mostrarErro("Turno invalido. Opcoes validas: Manha, Tarde, Noite.");
            }
        }
    }

    String lerComValidacao(String campo, int min, int max) {
        String input = "";
        boolean valido = false;

        while (!valido) {
            limparConsole();
            System.out.printf("Informe %s: %-1s %n", campo, "");
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
                case "cep (somente numeros)":
                    if (!input.matches("\\d{8}")) {
                        mostrarErro("CEP deve conter exatamente 8 numeros.");
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
        limparConsole();
        System.err.println("╔═════════════════════════════════════════════════════════╗");
        System.err.printf("║ ERRO: %-49s ║%n", mensagem);
        System.err.println("╚═════════════════════════════════════════════════════════╝");
        pausarConsole();
        limparConsole();
    }
}
