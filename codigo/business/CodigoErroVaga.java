package business;
public enum CodigoErroVaga implements CodigoErro {
    VAGA_NAO_FINALIZADA(401) {
        @Override
        public int getCodigo() {
            return 1;
        }
    };

    private final int numero;

    private CodigoErroVaga(int numero) {
        this.numero = numero;
    }

    @Override
    public int getNumero() {
        return numero;
    }
}
