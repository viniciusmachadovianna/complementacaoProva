package main.observer;

import main.Atendimento;

public class AvisoRecepcao implements Observador {
    @Override
    public void atualizar(Atendimento a) {
        if ("Finalizado".equals(a.getSituacao().getNome())) {
            System.out.printf("[Aviso → Recepção] Atendimento de %s FINALIZADO. "
                + "Valor total: R$ %.2f%n",
                a.getAnimal().getNome(), a.getValorFinal());
        }
    }
}