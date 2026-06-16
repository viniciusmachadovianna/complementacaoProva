package main;

public class ServicoVeterinario {
    private final String descricao;
    private final double valorBase;

    public ServicoVeterinario(String descricao, double valorBase) {
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    public String getDescricao() { return descricao; }
    public double getValorBase() { return valorBase; }
}