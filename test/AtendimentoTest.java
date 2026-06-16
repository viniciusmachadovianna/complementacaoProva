package main;

import main.*;
import main.state.*;
import main.observer.*;
import main.valor.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class AtendimentoTest {

    private Tutor tutor;
    private Veterinario veterinario;
    private ServicoVeterinario consulta;
    private ByteArrayOutputStream saida;

    @BeforeEach
    void configurar() {
        tutor       = new Tutor("Maria Silva", "32 98888-0001", "maria@email.com");
        veterinario = new Veterinario("Dr. Carlos Mendes", "MG-12345");
        consulta    = new ServicoVeterinario("Consulta Clínica Geral", 150.00);
        saida       = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
    }

    @AfterEach
    void restaurar() { System.setOut(System.out); }

    //Agendado → EmAtendimento
    @Test
    void deveTransitarDeAgendadoParaEmAtendimento() {
        Atendimento a = new Atendimento("A001", tutor,
            new Animal("Rex", "Cão", false), consulta, veterinario);
        assertEquals("Agendado", a.getSituacao().getNome());
        a.iniciar();
        assertEquals("EmAtendimento", a.getSituacao().getNome());
    }

    //EmAtendimento -> Finalizado
    @Test
    void deveTransitarDeEmAtendimentoParaFinalizado() {
        Atendimento a = new Atendimento("A002", tutor,
            new Animal("Mimi", "Gato", false), consulta, veterinario);
        a.iniciar();
        a.finalizar();
        assertEquals("Finalizado", a.getSituacao().getNome());
    }

    //Agendado -> Cancelado
    @Test
    void deveTransitarDeAgendadoParaCancelado() {
        Atendimento a = new Atendimento("A003", tutor,
            new Animal("Bolinha", "Hamster", false), consulta, veterinario);
        a.cancelar();
        assertEquals("Cancelado", a.getSituacao().getNome());
    }

    // Finalizado NÃO pode ser cancelado
    @Test
    void naoDeveCancelarAtendimentoFinalizado() {
        Atendimento a = new Atendimento("A004", tutor,
            new Animal("Buddy", "Cão", false), consulta, veterinario);
        a.iniciar();
        a.finalizar();
        IllegalStateException ex = assertThrows(
            IllegalStateException.class, a::cancelar);
        assertTrue(ex.getMessage().contains("finalizado"));
    }

    //EmAtendimento NÃO pode ser cancelado
    @Test
    void naoDeveCancelarAtendimentoEmAndamento() {
        Atendimento a = new Atendimento("A005", tutor,
            new Animal("Luna", "Gato", true), consulta, veterinario);
        a.iniciar();
        assertThrows(IllegalStateException.class, a::cancelar);
    }

    //Agendado NÃO pode ser finalizado diretamente
    @Test
    void naoDeveFinalizarAtendimentoAgendado() {
        Atendimento a = new Atendimento("A006", tutor,
            new Animal("Toby", "Cão", false), consulta, veterinario);
        assertThrows(IllegalStateException.class, a::finalizar);
    }

    //Cancelado NÃO pode ser reaberto
    @Test
    void naoDeveReabrirAtendimentoCancelado() {
        Atendimento a = new Atendimento("A007", tutor,
            new Animal("Mel", "Coelho", false), consulta, veterinario);
        a.cancelar();
        assertThrows(IllegalStateException.class, a::iniciar);
    }

    //Tutor avisado ao iniciar
    @Test
    void devAvisarTutorAoIniciar() {
        Atendimento a = new Atendimento("A008", tutor,
            new Animal("Fifi", "Gato", false), consulta, veterinario);
        a.adicionarObservador(new AvisoTutor());
        a.iniciar();
        String log = saida.toString();
        assertTrue(log.contains("Tutor") && log.contains("Maria Silva")
                   && log.contains("INICIADO"));
    }

    //Veterinário avisado ao cancelar
    @Test
    void deveAvisarVeterinarioAoCancelar() {
        Atendimento a = new Atendimento("A009", tutor,
            new Animal("Léo", "Cão", false), consulta, veterinario);
        a.adicionarObservador(new AvisoVeterinario());
        a.cancelar();
        String log = saida.toString();
        assertTrue(log.contains("Veterinário") && log.contains("Dr. Carlos Mendes")
                   && log.contains("CANCELADO"));
    }

    //Recepção avisada ao finalizar
    @Test
    void deveAvisarRecepcaoAoFinalizar() {
        Atendimento a = new Atendimento("A010", tutor,
            new Animal("Pingo", "Cão", false), consulta, veterinario);
        a.adicionarObservador(new AvisoRecepcao());
        a.iniciar();
        a.finalizar();
        String log = saida.toString();
        assertTrue(log.contains("Recepção") && log.contains("FINALIZADO"));
    }

    //Sem decoradores, valor base
    @Test
    void deveRetornarValorBase() {
        Atendimento a = new Atendimento("A012", tutor,
            new Animal("Rex", "Cão", false), consulta, veterinario);
        assertEquals(150.00, a.getValorFinal(), 0.001);
    }

    //15% animal adotado
    @Test
    void deveAplicarDesconto() {
        Atendimento a = new Atendimento("A013", tutor,
            new Animal("Bob", "Cão", true), consulta, veterinario);
        a.setCalculador(new DescontoAnimalAdotado(new CalculadorBase()));
        assertEquals(127.50, a.getValorFinal(), 0.001); // 150 - 15%
    }

    //add Taxa domiciliar
    @Test
    void deveAdicionarTaxaDomiciliar() {
        Atendimento a = new Atendimento("A014", tutor,
            new Animal("Pop", "Cão", false), consulta, veterinario);
        a.setCalculador(new TaxaAtendimentoDomiciliar(new CalculadorBase()));
        assertEquals(230.00, a.getValorFinal(), 0.001); // 150 + 80
    }

    //adotado + domiciliar + banho
    @Test
    void deveComporTresRegras() {
        Atendimento a = new Atendimento("A015", tutor,
            new Animal("Pipoca", "Gato", true), consulta, veterinario);
        a.setCalculador(
            new BanhoPosConsulta(
                new TaxaAtendimentoDomiciliar(
                    new DescontoAnimalAdotado(
                        new CalculadorBase()))));
        // 150 - 15% = 127,50  ->  +80 = 207,50  ->  +55 = 262,50
        assertEquals(262.50, a.getValorFinal(), 0.001);
    }

    //Animal adotado + banho
    @Test
    void deveComporDuasRegras() {
        Atendimento a = new Atendimento("A016", tutor,
            new Animal("Lili", "Cão", true), consulta, veterinario);
        a.setCalculador(
            new BanhoPosConsulta(
                new DescontoAnimalAdotado(
                    new CalculadorBase())));
        // 150 - 15% = 127,50  ->  +55 = 182,50
        assertEquals(182.50, a.getValorFinal(), 0.001);
    }
}