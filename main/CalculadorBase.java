package main;

import main.Atendimento;

public class CalculadorBase implements CalculadorDeValor {
    @Override
    public double calcular(Atendimento a) {
        return a.getServico().getValorBase();
    }
}