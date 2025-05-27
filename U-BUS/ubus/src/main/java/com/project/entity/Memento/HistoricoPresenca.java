package com.project.entity.Memento;

import java.util.Stack;

public class HistoricoPresenca {
    private Stack<PresencaMemento> historico = new Stack<>();

    public void salvarEstado(GerenciadorPresenca gerenciador) {
        historico.push(gerenciador.salvarEstado());
    }

    public boolean desfazerUltimaAcao(GerenciadorPresenca gerenciador) {
        if (!historico.isEmpty()) {
            PresencaMemento memento = historico.pop();
            gerenciador.restaurarEstado(memento);
            return true;
        }
        return false;
    }
}