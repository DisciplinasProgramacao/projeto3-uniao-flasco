package business.Plano;

import java.time.LocalTime;

public enum Turnos {
    MANHA("Manhã", LocalTime.of(8, 0), LocalTime.of(12, 0)),
    TARDE("Tarde", LocalTime.of(12, 1), LocalTime.of(18, 0)),
    NOITE("Noite", LocalTime.of(18, 1), LocalTime.of(23, 59));

    private final String descricao;
    private final LocalTime inicio;
    private final LocalTime fim;

    Turnos(String descricao, LocalTime inicio, LocalTime fim) {
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFim() {
        return fim;
    }
}
