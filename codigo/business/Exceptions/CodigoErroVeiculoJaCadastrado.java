package business.Exceptions;

public class CodigoErroVeiculoJaCadastrado extends Exception {

    public CodigoErroVeiculoJaCadastrado() {
        super("Não é possível cadastrar esse veículo, pois ele já está cadastrado.");
    }

}
    
