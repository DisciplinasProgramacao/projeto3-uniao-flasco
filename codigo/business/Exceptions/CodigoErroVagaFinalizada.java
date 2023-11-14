package business.Exceptions;

public class CodigoErroVagaFinalizada extends Exception {

    public CodigoErroVagaFinalizada() {
        super("Não é possível sair da vaga. Ela já foi finalizada!");
    }

}
