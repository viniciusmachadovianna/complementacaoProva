package main;

import main.Atendimento;

/** Acréscimo de R$ 55,00 pelo banho pós-consulta. */
public class BanhoPosConsulta extends DecoradorDeValor {
    private static final double VALOR = 55.00;

    public BanhoPosConsulta(CalculadorDeValor decorado) { super(decorado); }

    @Override
    public double calcular(Atendimento a) {
        return super.calcular(a) + VALOR;
    }
}