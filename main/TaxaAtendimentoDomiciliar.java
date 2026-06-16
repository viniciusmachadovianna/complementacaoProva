package main;

import main.Atendimento;

/** +80 domicilio*/
public class TaxaAtendimentoDomiciliar extends DecoradorDeValor {
    private static final double TAXA = 80.00;

    public TaxaAtendimentoDomiciliar(CalculadorDeValor decorado) { super(decorado); }

    @Override
    public double calcular(Atendimento a) {
        return super.calcular(a) + TAXA;
    }
}