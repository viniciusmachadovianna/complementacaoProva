package main.valor;

import main.Atendimento;

/** -15% p/ adotados */
public class DescontoAnimalAdotado extends DecoradorDeValor {
    private static final double DESCONTO = 0.15;

    public DescontoAnimalAdotado(CalculadorDeValor decorado) { super(decorado); }

    @Override
    public double calcular(Atendimento a) {
        double valor = super.calcular(a);
        return a.getAnimal().isAdotado() ? valor * (1 - DESCONTO) : valor;
    }
}