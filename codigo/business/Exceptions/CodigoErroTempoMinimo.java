package business.Exceptions;

public enum CodigoErroTempoMinimo implements CodigoErro {
    TEMPO_MINIMO(701){
        @Override
        public int getCodigo() {
            return 1;
        }
    };
    private final int numero;

    private CodigoErroTempoMinimo(int numero){
        this.numero = numero;
    }
    
    @Override
    public int getNumero(){
        return numero;
    }
    }
    
