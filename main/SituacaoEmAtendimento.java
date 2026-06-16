package main;

import main.Atendimento;

public class SituacaoEmAtendimento implements SituacaoAtendimento {

    @Override
    public void iniciar(Atendimento a) {
        throw new IllegalStateException("O atendimento já está em andamento.");
    }

    @Override
    public void finalizar(Atendimento a) {
        a.setSituacao(new SituacaoFinalizado());
        a.notificarObservadores();
    }

    @Override
    public void cancelar(Atendimento a) {
        throw new IllegalStateException(
            "Não é possível cancelar um atendimento em andamento.");
    }

    @Override
    public String getNome() { return "EmAtendimento"; }
}