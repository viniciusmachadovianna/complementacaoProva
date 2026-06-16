package main.observer;

import main.Atendimento;

public class AvisoVeterinario implements Observador {
    @Override
    public void atualizar(Atendimento a) {
        if ("Cancelado".equals(a.getSituacao().getNome())) {
            System.out.println("[Aviso → Veterinário] "
                + a.getVeterinario().getNome()
                + ": atendimento de " + a.getAnimal().getNome()
                + " foi CANCELADO.");
        }
    }
}