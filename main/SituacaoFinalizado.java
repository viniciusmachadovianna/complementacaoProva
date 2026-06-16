package main;

import main.Atendimento;

public class SituacaoFinalizado implements SituacaoAtendimento {

    @Override
    public void iniciar(Atendimento a) {
        throw new IllegalStateException("O atendimento já foi finalizado.");
    }

    @Override
    public void finalizar(Atendimento a) {
        throw new IllegalStateException("O atendimento já foi finalizado.");
    }

    @Override
    public void cancelar(Atendimento a) {
        throw new IllegalStateException(
            "Não é possível cancelar um atendimento já finalizado.");
    }

    @Override
    public String getNome() { return "Finalizado"; }
}