package com.project.entity.Memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.project.entity.Aluno;
//originator
public class GerenciadorPresenca {
    private List<Aluno> alunos;

    public GerenciadorPresenca(List<Aluno> alunos) {
        this.alunos = new ArrayList<>(alunos); 
    }

    public void marcarPresenca(Aluno aluno, boolean vaiParaAula) {
        aluno.setVaiParaAula(vaiParaAula);
    }

    public PresencaMemento salvarEstado() {
        return new PresencaMemento(new ArrayList<>(alunos)); 
    }

    public void restaurarEstado(PresencaMemento memento) {
        this.alunos = new ArrayList<>(memento.getEstadoSalvo()); 
    }

    public List<Aluno> getAlunos() {
        return Collections.unmodifiableList(alunos);
    }
}