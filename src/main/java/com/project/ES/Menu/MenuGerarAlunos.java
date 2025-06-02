package com.project.ES.Menu;
import com.project.entity.Aluno;

public class MenuGerarAlunos extends MenuBase{
    public void logoGerarAlunos() {
        limparConsole();
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║           GERAR ALUNOS           ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println();
    }

    public int perguntarQuantidadeAlunos() {
        boolean continuar = false;
        int quantidade = 0;

        while (!continuar) {
            limparConsole();
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║ Quantos alunos você deseja gerar?          ║");
            System.out.println("║ (Digite um número entre 1 e 300)           ║");
            System.out.println("╚════════════════════════════════════════════╝");
            String entrada = this.es.entradaString();

            try {
                quantidade = Integer.parseInt(entrada);
                if (quantidade >= 1 && quantidade <= 300) {
                    continuar = true;
                } else {
                    System.out.println("╔════════════════════════════════════════════╗");
                    System.out.println("║ ERRO: Digite um número entre 1 e 300.     ║");
                    System.out.println("╚════════════════════════════════════════════╝");
                    pausarConsole();
                }
            } catch (NumberFormatException e) {
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║ ERRO: Entrada inválida! Digite apenas números. ║");
                System.out.println("╚════════════════════════════════════════════╝");
                pausarConsole();
            }
        }

        return quantidade;
    }

    public void iniciarGeracaoAlunos() {
        logoGerarAlunos();
        int quantidade = perguntarQuantidadeAlunos();
        limparConsole();

        System.out.println();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.printf("║ Gerando %d aluno(s) automaticamente...%n", quantidade);
        System.out.println("╚════════════════════════════════════════════╝");

        for (int i = 0; i < quantidade; i++){
            Aluno aluno = new Aluno();
            aluno.gerarInfos();
            alDB.salvarAluno(aluno);
        }
    }
}
