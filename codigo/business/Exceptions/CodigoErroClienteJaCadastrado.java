package business.Exceptions;

public class CodigoErroClienteJaCadastrado extends Exception {
    
    public CodigoErroClienteJaCadastrado() {
        super("Não é possível cadastrar esse cliente, pois ele já está cadastrado.");
    }

}
