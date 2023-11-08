package business.Exceptions;

public enum CodigoErroVeiculoNaoEncontrado implements CodigoErro {
    VEICULO_NAO_ENCONTRADO(601){
        @Override
        public int getCodigo() {
            return 1;
        }
    };
    private final int numero;

    private CodigoErroVeiculoNaoEncontrado(int numero){
        this.numero = numero;
    }
    
    @Override
    public int getNumero(){
        return numero;
    }
    }
    
