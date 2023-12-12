package business.Estacionamento;

/**
 * Enumeração de meses para facilitar o uso de meses no código.
 */
public enum MesEnum {
    JANEIRO(1), FEVEREIRO(2), MARCO(3),
     ABRIL(4), MAIO(5), JUNHO(6),
      JULHO(7), AGOSTO(8), SETEMBRO(9),
       OUTUBRO(10), NOVEMBRO(11), DEZEMBRO(12);

    private int mes;

    /**
     * Construtor privado para associar um número inteiro a cada mês.
     *
     * @param mes O número inteiro representando o mês.
     */
    MesEnum(int mes) {
        this.mes = mes;
    }

    /**
     * Obtém o número inteiro associado a um mês.
     *
     * @return O número inteiro que representa o mês.
     */
    public int getMes() {
        return mes;
    }
    
    /**
     * Retorna o MesEnum correspondente ao número inteiro fornecido.
     *
     * @param mes O número inteiro do mês.
     * @return O MesEnum correspondente ao número inteiro fornecido.
     * @throws IllegalArgumentException Se o número inteiro não corresponder a nenhum mês.
     */
    public static MesEnum fromInt(int mes) {
        for (MesEnum mesEnum : MesEnum.values()) {
            if (mesEnum.getMes() == mes) {
                return mesEnum;
            }
        }
        throw new IllegalArgumentException("Mês inválido: " + mes);
    }
    
}
