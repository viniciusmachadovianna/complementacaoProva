package main;

import main.Atendimento;

public class AvisoTutor implements Observador {
    @Override
    public void atualizar(Atendimento a) {
        if ("EmAtendimento".equals(a.getSituacao().getNome())) {
            System.out.println("[Aviso → Tutor] " + a.getTutor().getNome()
                + ", o atendimento de " + a.getAnimal().getNome()
                + " foi INICIADO.");
        }
    }
}