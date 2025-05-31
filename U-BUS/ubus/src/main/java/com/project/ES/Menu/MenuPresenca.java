package com.project.ES.Menu;

import com.project.DB.DataBaseManager;
import com.project.entity.Admin;
import com.project.entity.Aluno;
import com.project.entity.Memento.GerenciadorPresenca;
import com.project.entity.Memento.HistoricoPresenca;
import com.project.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuPresenca {
    private Scanner scanner;
    private GerenciadorPresenca gerenciador;
    private HistoricoPresenca historico;

    public MenuPresenca() {
        this.scanner = new Scanner(System.in);
    }

    public void menuAluno(Aluno alunoLogado) {
        
        this.gerenciador = new GerenciadorPresenca(List.of(alunoLogado));
        this.historico = new HistoricoPresenca();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MINHA PRESENÇA ---");
            System.out.println("Aluno: " + alunoLogado.getNome());
            System.out.println("Instituição: " + alunoLogado.getInstituicao());
            System.out.println("\n[1] Marcar presença para hoje");
            System.out.println("[2] Ver meu status atual");
            System.out.println("[3] Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> marcarPresenca(alunoLogado);
                case "2" -> verStatus(alunoLogado);
                case "3" -> continuar = false;
                default -> System.out.println("Opção inválida!");
            }

            if (continuar) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }

   public void verListaPresenca(Usuario usuarioLogado) {
    if (!(usuarioLogado instanceof Admin)) {
        System.out.println("Somente administradores podem ver a lista de presença.");
        pausarConsole();
        limparConsole();
        return;
    }

    DataBaseManager db = new DataBaseManager("postgres", "pombal10");
    db.conectar();
    
    
    List<Aluno> alunos = db.consultarAlunos();

    if (alunos.isEmpty()) {
        System.out.println("Nenhum aluno cadastrado.");
    } else {
        
        List<Aluno> alunosPresentes = alunos.stream() // ver alunos que marcaram presenca
            .filter(Aluno::isVaiParaAula)
            .toList();

        if (alunosPresentes.isEmpty()) {
            System.out.println("Nenhum aluno marcou presença ainda.");
        } else {
            System.out.println("\n--- LISTA DE PRESENÇA (ATUALIZADA) ---");
            
            
            Map<String, List<Aluno>> alunosPorInstituicao = alunosPresentes.stream()
                .collect(Collectors.groupingBy(Aluno::getInstituicao)); // dividir por intituiçoes

            List<String> instituicoesOrdenadas = new ArrayList<>(alunosPorInstituicao.keySet());
            Collections.sort(instituicoesOrdenadas); // agrupar

            for (String instituicao : instituicoesOrdenadas) {
                System.out.println("\n--- " + instituicao.toUpperCase() + " ---");
                System.out.printf("%-20s %-15s\n", "NOME", "TURNO");
                System.out.println("----------------------------------");
                
                List<Aluno> alunosDaInstituicao = alunosPorInstituicao.get(instituicao);
                alunosDaInstituicao.sort(Comparator.comparing(Aluno::getNome));
                
                for (Aluno aluno : alunosDaInstituicao) {
                    System.out.printf("%-20s %-15s\n", 
                        aluno.getNome_Aluno(),
                        aluno.getTurno());
                }
            }
            
            System.out.println("\nTotal de alunos presentes: " + alunosPresentes.size());
        }
    }
    pausarConsole();
    limparConsole();
}

private void marcarPresenca(Aluno aluno) {
    historico.salvarEstado(gerenciador);// salva o historico antigo
    
    System.out.print("\nVocê vai para a aula hoje? (S/N): ");
    String resposta = scanner.nextLine();
    
    boolean vaiParaAula = resposta.equalsIgnoreCase("S");
    
    
    DataBaseManager db = new DataBaseManager("postgres", "pombal10");
    db.conectar();
    boolean atualizado = db.atualizarPresencaAluno(aluno.getCpf(), vaiParaAula);
    
    if (atualizado) {
        
        gerenciador.marcarPresenca(aluno, vaiParaAula);
        System.out.println("Presença marcada: " +  (vaiParaAula ? "VOU para a aula" : "NÃO VOU para a aula"));
    } else {
        System.out.println("Erro ao atualizar presença no banco de dados!");
    }
}

    private void verStatus(Aluno aluno) {
        System.out.println("\n--- MEU STATUS ATUAL ---");
        System.out.println("Status: " + (aluno.isVaiParaAula() ? "VOU para a aula" : "NÃO VOU para a aula"));
    }


    private void pausarConsole() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    private void limparConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Erro ao limpar console: " + e.getMessage());
        }
    }
    public void atualizarListaPresenca() {
    DataBaseManager db = new DataBaseManager("postgres", "pombal10");
    db.conectar();
    db.consultarAlunos(); 
}
}