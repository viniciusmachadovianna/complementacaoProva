package main;

import main.Atendimento;

public class SituacaoCancelado implements SituacaoAtendimento {

    @Override
    public void iniciar(Atendimento a) {
        throw new IllegalStateException("Não é possível reabrir um atendimento cancelado.");
    }

    @Override
    public void finalizar(Atendimento a) {
        throw new IllegalStateException("Não é possível finalizar um atendimento cancelado.");
    }

    @Override
    public void cancelar(Atendimento a) {
        throw new IllegalStateException("O atendimento já está cancelado.");
    }

    @Override
    public String getNome() { return "Cancelado"; }
}