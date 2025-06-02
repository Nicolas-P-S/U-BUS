package com.project.entity.MementoInterator;

import com.project.entity.*;
import com.project.ES.Menu.MenuBase;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Lista extends MenuBase{
    private List<Aluno> alunos = new ArrayList<Aluno>();
    private List<Motorista> motoristas = new ArrayList<Motorista>();
    private Map<String, List<Aluno>> porInstituicao = new HashMap<>();
    private Map<Motorista, List<Aluno>> listaFinal = new HashMap<>();
    final private int vagasOnibus = 51;

    public void adicionarAlunos(){
        alunos.clear();
        alunos = alDB.consultarAlunos();
    }

    public void adicionarMotorista(){
        motoristas.clear();
        motoristas = mDB.consultarMotoristas();
    }

    public void divirAlunosPorInstituicao(){
        porInstituicao.clear();
        for (Aluno aluno : alunos){
            String instituicao = aluno.getInstituicao();
            List<Aluno> lista = porInstituicao.get(instituicao);

            if (lista == null){
                lista = new ArrayList<>();
                porInstituicao.put(instituicao, lista);
            }
            lista.add(aluno);
        }
    }

    public void gerarLista() {
        listaFinal.clear();
        int totalVagas = vagasOnibus * motoristas.size();

        System.out.println("Total de alunos: " + alunos.size());
        System.out.println("Total de motoristas: " + motoristas.size());
        System.out.println("Total de vagas disponíveis: " + totalVagas);

        if (totalVagas >= alunos.size()) {
            listaFinal.clear();

            List<Aluno> todosAlunos = new ArrayList<>();
            for (List<Aluno> alunos : porInstituicao.values()) {
                todosAlunos.addAll(alunos);
            }

            int indiceOnibus = 0;
            int totalAlunos = todosAlunos.size();

            for (int i = 0; i < totalAlunos; i += vagasOnibus) {
                if (indiceOnibus >= motoristas.size()) {
                    System.out.println("ERRO: Não há motoristas suficientes para todos os grupos de alunos.");
                    break;
                }

                int fimDaLista = Math.min(i + vagasOnibus, totalAlunos);
                List<Aluno> grupo = new ArrayList<>(todosAlunos.subList(i, fimDaLista));

                listaFinal.put(motoristas.get(indiceOnibus), grupo);
                indiceOnibus++;
            }

        } else {
            System.out.println("ERRO: Quantidade de onibus insuficiente!");
            pausarConsole();
        }
    }


    public void imprimirLista() {
        limparConsole();
        if (listaFinal.size() != 0){
            for (Map.Entry<Motorista, List<Aluno>> entrada : listaFinal.entrySet()) {
                System.out.println(entrada.getKey().getNome() + ":");
    
                int contador = 0;
                for (Aluno aluno : entrada.getValue()) {
                    contador ++;
                    System.out.println(contador+"- " + aluno.getNome() + " (" + aluno.getInstituicao() + ")");
                }
    
                System.out.println();
            }
            
        }
        else{
            System.out.println("Não há lista gerada!");
        }
    }
}
