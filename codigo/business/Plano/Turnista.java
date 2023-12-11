package business.Plano;



/**
 * Representa um tipo de plano de estacionamento específico, derivado do plano Horista.
 * O plano Turnista é baseado em turnos específicos de utilização.
 */
public class Turnista extends Horista {
    public Turnos turno;

    /**
     * Construtor que define o tipo de plano e o turno específico associado.
     *
     * @param tipo  O tipo de plano.
     * @param turno O turno associado ao plano Turnista.
     */
    public Turnista(String tipo, Turnos turno) {
        super(tipo);
        setTurno(turno);
    }

    /**
     * Obtém o turno associado ao plano Turnista.
     *
     * @return O turno associado.
     */
    public Turnos getTurno() {
        return turno;
    }

    /**
     * Define o turno associado ao plano Turnista.
     *
     * @param turno O turno a ser definido.
     */
    public void setTurno(Turnos turno) {
        this.turno = turno;
    }
}
