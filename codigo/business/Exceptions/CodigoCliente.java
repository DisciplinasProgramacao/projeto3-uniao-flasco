package business.Exceptions;

public enum CodigoCliente implements CodigoErro {
    CLIENTE_NAO_ENCONTRADO(201) {
        @Override
        public int getCodigo() {
            return 1;
        }
    },
    CLIENTE_JA_EXISTE(202) {
        @Override
        public int getCodigo() {
            return 2;
        }
    };

    private final int numero;

    private CodigoCliente(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

}
