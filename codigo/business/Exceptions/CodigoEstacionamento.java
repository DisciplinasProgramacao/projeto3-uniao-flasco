package business.Exceptions;

public enum CodigoEstacionamento implements CodigoErro {
    ESTACIONAMENTO_NAO_ENCONTRADO(401) {
        @Override
        public int getCodigo() {
            return 1;
        }
    };

    private final int numero;

    private CodigoEstacionamento(int numero) {
        this.numero = numero;
    }

    @Override
    public int getNumero() {
        return numero;
    }
}
