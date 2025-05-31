package com.project.entity.Memento;

import com.project.entity.Aluno;
import java.util.List;

public class PresencaMemento {
    private final List<Aluno> estadoSalvo;

    public PresencaMemento(List<Aluno> alunos) {
        
        this.estadoSalvo = alunos.stream()
            .map(a -> {
                Aluno copia = new Aluno(
                    a.getNome(),
                    a.getSenha(),
                    a.getNome_Aluno(),
                    a.getSobrenome_Aluno(),
                    a.getCpf(),
                    a.getCep(),
                    a.getEnderco(),
                    a.getBairro(),
                    a.getCurso(),
                    a.getSemestre(),
                    a.getTurno(),
                    a.getInstituicao(),
                    a.getTelefone(),
                    a.getEmail()
                );
                copia.setVaiParaAula(a.isVaiParaAula());
                return copia;
            })
            .toList();
    }

    public List<Aluno> getEstadoSalvo() {
        return estadoSalvo;
    }
}