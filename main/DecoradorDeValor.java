package main;

import main.Atendimento;

public abstract class DecoradorDeValor implements CalculadorDeValor {
    protected final CalculadorDeValor decorado;

    protected DecoradorDeValor(CalculadorDeValor decorado) {
        this.decorado = decorado;
    }

    @Override
    public double calcular(Atendimento a) {
        return decorado.calcular(a);
    }
}