package main.observer;

import main.Atendimento;

public interface Observador {
    void atualizar(Atendimento atendimento);
}