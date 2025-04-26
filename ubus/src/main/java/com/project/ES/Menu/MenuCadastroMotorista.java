package com.project.ES.Menu;

import com.project.ES.ES;
import com.project.entity.Motorista;
import com.project.entity.Usuario;

public class MenuCadastroMotorista extends MenuBase {
    ES es = new ES();

    public Motorista cadastrarMotorista() {
        limparConsole();
        System.out.println("----- CADASTRO DO MOTORISTA -----\n");

        MenuCadatro cadastroBasico = new MenuCadatro();
        Usuario usuario = cadastroBasico.inicioCadastro();
        String nome = usuario.getNome();
        String senha = usuario.getSenha();

        String cpf = lerComValidacao("CPF (somente números)", 11, 11);
        String endereco = lerComValidacao("Endereço", 0, 100);
        String cnh = lerComValidacao("CNH", 9, 9);
        String categoriaCNH = lerComValidacao("Categoria da CNH: ", 1, 3);
        String email = lerComValidacao("Email", 5, 100);
        String telefone = lerComValidacao("Telefone (com DDD)", 9, 11);

        return new Motorista(nome, senha, cpf, cnh, categoriaCNH, telefone, email, endereco);
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
                case "cnh":
                    if (!input.matches("\\d{9}")) {
                        System.out.println("CNH deve conter exatamente 9 números.");
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
