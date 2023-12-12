package business.Plano;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Enumeração que representa os diferentes turnos do dia.
 */
public enum Turnos implements Serializable{
    MANHA("Manhã", LocalTime.of(8, 0), LocalTime.of(12, 0)),
    TARDE("Tarde", LocalTime.of(12, 1), LocalTime.of(18, 0)),
    NOITE("Noite", LocalTime.of(18, 1), LocalTime.of(23, 59));

    private final String descricao;
    private final LocalTime inicio;
    private final LocalTime fim;

    /**
     * Construtor da enumeração Turnos.
     *
     * @param descricao A descrição do turno.
     * @param inicio    O horário de início do turno.
     * @param fim       O horário de término do turno.
     */
    Turnos(String descricao, LocalTime inicio, LocalTime fim) {
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
    * Obtém a descrição do turno.
    *
    * @return A descrição do turno.
    */
    public String getDescricao() {
        return descricao;
    }

    /**
    * Obtém o horário de início do turno.
    *
    * @return O horário de início do turno.
    */
    public LocalTime getInicio() {
        return inicio;
    }

    /**
    * Obtém o horário de término do turno.
    *
    * @return O horário de término do turno.
    */
    public LocalTime getFim() {
        return fim;
    }
}
