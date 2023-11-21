package business.Plano;

public class Turnista extends Horista {
    public Turnos turno;
    public Turnista(String tipo, Turnos turno) {
        super(tipo);
        setTurno(turno);;
    }

    public Turnos getTurno() {
        return turno;
    }
    public void setTurno(Turnos turno) {
        this.turno = turno;
    }
}
