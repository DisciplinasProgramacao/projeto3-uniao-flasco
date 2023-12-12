package business.Exceptions;
import java.util.Map;
import java.util.TreeMap;


/**
 * Classe que representa uma exceção geral para situações específicas.
 * Estende RuntimeException para permitir exceções não verificadas.
 */
public class ExcecaoGeral extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodigoErro codigoErro;
    private Map<String, Object> propriedades = new TreeMap<String, Object>();

    /**
     * Obtém o código de erro associado à exceção.
     *
     * @return O código de erro associado.
     */
    public CodigoErro getCodigoErro() {
        return codigoErro;
    }

    /**
     * Define o código de erro associado à exceção.
     *
     * @param erro O código de erro a ser associado.
     * @return A própria instância da exceção para permitir operações encadeadas.
     */
    public ExcecaoGeral setCodigoErro(CodigoErro erro) {
        codigoErro = erro;
        return this;
    }

    /**
     * Obtém as propriedades associadas à exceção.
     *
     * @return Um mapa contendo as propriedades da exceção.
     */
    public Map<String, Object> getPropriedades() {
        return propriedades;
    }

    /**
     * Obtém um valor específico associado a uma chave no mapa de propriedades.
     *
     * @param key A chave associada ao valor desejado.
     * @param <T> O tipo do valor associado à chave.
     * @return O valor associado à chave especificada.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) propriedades.get(key);
    }

    /**
     * Define uma propriedade associada à exceção.
     *
     * @param key   A chave para a propriedade.
     * @param value O valor associado à chave.
     * @return A própria instância da exceção para permitir operações encadeadas.
     */
    public ExcecaoGeral set(String key, Object value) {
        propriedades.put(key, value);
        return this;
    }

    /**
     * Obtém a mensagem de erro da exceção.
     *
     * @return A mensagem de erro da exceção, que inclui o código de erro associado.
     */
    @Override
    public String getMessage() {
        return "Exceção Geral de código: " + codigoErro;
    }
}

