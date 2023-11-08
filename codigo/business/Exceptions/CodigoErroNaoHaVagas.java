package business.Exceptions;

public enum CodigoErroNaoHaVagas implements CodigoErro {
    NAO_HA_VAGAS(501){
        @Override
        public int getCodigo() {
            return 1;
        }
    };
    private final int numero;

    private CodigoErroNaoHaVagas(int numero){
        this.numero = numero;
    }
    
    @Override
    public int getNumero(){
        return numero;
    }
    }
    
