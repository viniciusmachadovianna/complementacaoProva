package main.state;

import main.Atendimento;

public class SituacaoAgendado implements SituacaoAtendimento {

    @Override
    public void iniciar(Atendimento a) {
        a.setSituacao(new SituacaoEmAtendimento());
        a.notificarObservadores();
    }

    @Override
    public void finalizar(Atendimento a) {
        throw new IllegalStateException(
            "Não é possível finalizar um atendimento que ainda não foi iniciado.");
    }

    @Override
    public void cancelar(Atendimento a) {
        a.setSituacao(new SituacaoCancelado());
        a.notificarObservadores();
    }

    @Override
    public String getNome() { return "Agendado"; }
}