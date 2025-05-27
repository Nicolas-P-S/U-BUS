package com.project.ES.Menu;

import com.project.ES.ES;
import com.project.entity.Aluno;
import com.project.entity.Usuario;

public class MenuCadastroAluno extends MenuBase {
    ES es = new ES();

    public Aluno cadastrarAluno() {
        limparConsole();
        System.out.println("----- CADASTRO DO ALUNO -----\n");

        MenuCadatro cadastroBasico = new MenuCadatro();
        Usuario usuario = cadastroBasico.inicioCadastro();
        String nome = usuario.getNome();
        String senha = usuario.getSenha();

        String cpf = lerComValidacao("CPF (somente números)", 11, 11);
        String cep = lerComValidacao("CEP (somente números)", 8, 8);
        String endereco = lerComValidacao("Endereço", 5, 100);
        String bairro = lerComValidacao("Bairro", 3, 50);
        String curso = lerComValidacao("Curso", 3, 50);
        int semestre = lerSemestre();
        String turno = lerTurno();
        String instituicao = lerComValidacao("Instituição de Ensino", 3, 100);
        String telefone = lerComValidacao("Telefone (com DDD)", 10, 11);
        String email = lerComValidacao("Email", 5, 100);

        return new Aluno(nome, senha, cpf, cep, endereco, bairro, curso, 
                        semestre, turno, instituicao, telefone, email);
    }

    private int lerSemestre() {
        while (true) {
            System.out.print("Semestre (1-12): ");
            String input = es.entradaString().trim();
            
            try {
                int semestre = Integer.parseInt(input);
                if (semestre >= 1 && semestre <= 12) {
                    return semestre;
                } else {
                    System.out.println("O semestre deve ser um número entre 1 e 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido para o semestre.");
            }
        }
    }

    private String lerTurno() {
        while (true) {
            System.out.print("Turno (Manhã/Tarde/Noite): ");
            String input = es.entradaString().trim().toLowerCase();
            
            if (input.equals("manhã") || input.equals("manha") || 
                input.equals("tarde") || input.equals("noite")) {
                return input.substring(0, 1).toUpperCase() + input.substring(1);
            } else {
                System.out.println("Turno inválido. Opções válidas: Manhã, Tarde, Noite");
            }
        }
    }

    String lerComValidacao(String campo, int min, int max) {
        String input = "";
        boolean valido = false;

        while (!valido) {
            System.out.print(campo + ": ");
            input = es.entradaString().trim();

            if (input.length() < min || input.length() > max) {
                System.out.println("O campo \"" + campo + "\" deve ter entre " + min + " e " + max + " caracteres.\n");
                continue;
            }

            switch (campo.toLowerCase()) {
                case "cpf (somente números)":
                    if (!input.matches("\\d{11}")) {
                        System.out.println("CPF deve conter exatamente 11 números.");
                        continue;
                    }
                    break;
                case "cep (somente números)":
                    if (!input.matches("\\d{8}")) {
                        System.out.println("CEP deve conter exatamente 8 números.");
                        continue;
                    }
                    break;
                case "telefone (com ddd)":
                case "telefone":
                    if (!input.matches("\\d{10,11}")) {
                        System.out.println("Telefone deve conter entre 10 e 11 dígitos numéricos.");
                        continue;
                    }
                    break;
                case "email":
                    if (!input.contains("@") || !input.contains(".")) {
                        System.out.println("E-mail inválido. Verifique o formato (ex: exemplo@email.com).");
                        continue;
                    }
                    break;
            }

            valido = true;
        }

        return input;
    }
}