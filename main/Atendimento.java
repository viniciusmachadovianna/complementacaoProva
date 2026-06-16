package main;

import main.estado.SituacaoAgendado;
import main.estado.SituacaoAtendimento;
import main.observador.Observador;
import main.valor.CalculadorDeValor;
import main.valor.CalculadorBase;
import java.util.ArrayList;
import java.util.List;

public class Atendimento {

    private final String id;
    private final Tutor tutor;
    private final Animal animal;
    private final ServicoVeterinario servico;
    private final Veterinario veterinario;

    private SituacaoAtendimento situacao;
    private final List<Observador> observadores = new ArrayList<>();
    private CalculadorDeValor calculador;

    public Atendimento(String id, Tutor tutor, Animal animal,
                       ServicoVeterinario servico, Veterinario veterinario) {
        this.id          = id;
        this.tutor       = tutor;
        this.animal      = animal;
        this.servico     = servico;
        this.veterinario = veterinario;
        this.situacao    = new SituacaoAgendado();
        this.calculador  = new CalculadorBase();
    }

    public String getId()                      { return id; }
    public Tutor getTutor()                    { return tutor; }
    public Animal getAnimal()                  { return animal; }
    public ServicoVeterinario getServico()     { return servico; }
    public Veterinario getVeterinario()        { return veterinario; }
    public SituacaoAtendimento getSituacao()   { return situacao; }

    public void setSituacao(SituacaoAtendimento nova) { this.situacao = nova; }
    public void iniciar()   { situacao.iniciar(this); }
    public void finalizar() { situacao.finalizar(this); }
    public void cancelar()  { situacao.cancelar(this); }

    public void adicionarObservador(Observador o) { observadores.add(o); }
    public void removerObservador(Observador o)   { observadores.remove(o); }
    public void notificarObservadores() {
        for (Observador o : observadores) o.atualizar(this);
    }

    public void setCalculador(CalculadorDeValor c) { this.calculador = c; }
    public double getValorFinal() {
        return calculador.calcular(this);
    }
}