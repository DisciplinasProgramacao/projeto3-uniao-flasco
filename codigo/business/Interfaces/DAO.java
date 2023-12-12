package business.Interfaces;
import java.util.List;

/**
 * Interface genérica representando um Data Access Object (DAO) para operações básicas de persistência.
 *
 * @param <T> O tipo de entidade a ser manipulada pelo DAO.
 * @param <K> O tipo da chave usada para identificação única da entidade.
 */
public interface DAO<T, K> {

    /**
     * Obtém um objeto do tipo T usando uma chave do tipo K.
     *
     * @param chave A chave usada para identificar o objeto a ser obtido.
     * @return O objeto do tipo T associado à chave fornecida, ou null se não encontrado.
     */
    public T get(K chave);

    /**
     * Adiciona um objeto do tipo T.
     *
     * @param p O objeto do tipo T a ser adicionado.
     */
    public void add(T p);

    /**
     * Atualiza um objeto do tipo T.
     *
     * @param p O objeto do tipo T a ser atualizado.
     */
    public void update(T p);

    /**
     * Deleta um objeto do tipo T.
     *
     * @param p O objeto do tipo T a ser removido.
     */
    public void delete(T p);

    /**
     * Obtém uma lista de todos os objetos do tipo T.
     *
     * @return Uma lista contendo todos os objetos do tipo T.
     */
    public List<T> getAll();
}
