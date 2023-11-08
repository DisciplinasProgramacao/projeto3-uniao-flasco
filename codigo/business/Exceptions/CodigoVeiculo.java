package business.Exceptions;

public enum CodigoVeiculo implements CodigoErro {
    VEICULO_JA_EXISTE(301){
        @Override
        public int getCodigo() {
            return 1;
        }
    },
    VEICULO_NAO_ENCONTRADO(302){
        @Override
        public int getCodigo() {
            return 2;
        }
    };
    private final int numero;

    private CodigoVeiculo(int numero){
        this.numero = numero;
    }
    
    @Override
    public int getNumero(){
        return numero;
    }
    }
    
