package business.Exceptions;

public class CodigoErroTempoMinimo extends Exception {

    public CodigoErroTempoMinimo() {
        super("O tempo desejado é inferior ao mínimo do serviço. Por favor selecione novamente.");
    }

}
    
