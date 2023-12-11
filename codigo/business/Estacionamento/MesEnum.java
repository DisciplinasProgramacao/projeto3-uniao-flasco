package business.Estacionamento;
/*
 * Classe Enumerada utilizada para facilitar a utilização de mês no código
 */
public enum MesEnum {
    JANEIRO(1), FEVEREIRO(2), MARCO(3),
     ABRIL(4), MAIO(5), JUNHO(6),
      JULHO(7), AGOSTO(8), SETEMBRO(9),
       OUTUBRO(10), NOVEMBRO(11), DEZEMBRO(12);

    private int mes;

    MesEnum(int mes) {
        this.mes = mes;
    }

    public int getMes() {
        return mes;
    }
    
    public static MesEnum fromInt(int mes) {
        for (MesEnum mesEnum : MesEnum.values()) {
            if (mesEnum.getMes() == mes) {
                return mesEnum;
            }
        }
        throw new IllegalArgumentException("Mês inválido: " + mes);
    }
    
}
