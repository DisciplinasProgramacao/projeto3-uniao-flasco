package business.Estacionamento;



import java.io.*;
import java.util.ArrayList;
import java.util.List;


import business.Interfaces.DAO;

/**
 * Classe responsável por realizar operações de persistência para objetos Estacionamento.
 * Implementa a interface DAO para fornecer métodos de acesso aos dados.
 * 
 * @param <Estacionamento> O tipo de objeto Estacionamento a ser persistido.
 * @param <Integer>        O tipo de identificador para os objetos Estacionamento.
 */
public class GenericDAO implements DAO<Estacionamento, Integer> {
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    /**
     * Inicializa o GenericDAO com o nome do arquivo onde os dados serão armazenados.
     *
     * @param filename O nome do arquivo de armazenamento.
     * @throws IOException Se houver erro na criação ou acesso ao arquivo.
     */
    public GenericDAO(String filename) throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            
            file.createNewFile();
        }
        fos = new FileOutputStream(file, false); 
        outputFile = new ObjectOutputStream(fos);
    }

    /**
     * Adiciona um objeto Estacionamento ao arquivo de armazenamento.
     *
     * @param estacionamento O Estacionamento a ser adicionado.
     */
    public void add(Estacionamento estacionamento) {
        try {
            outputFile.writeObject(estacionamento);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar o estacionamento '" + estacionamento.getNome() + "' no disco!");
            e.printStackTrace();
        }
    }

    /**
     * Retorna um objeto Estacionamento com base na chave (ID) fornecida.
     *
     * @param chave A chave (ID) do Estacionamento a ser recuperado.
     * @return O Estacionamento com a chave correspondente ou null se não encontrado.
     */
    public Estacionamento get(Integer chave) {
        Estacionamento estacionamento = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                estacionamento = (Estacionamento) inputFile.readObject();

                if (chave.equals(estacionamento.getId())) {
                    return estacionamento;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o estacionamento '" + chave + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos Estacionamento armazenados no arquivo.
     *
     * @return Uma lista contendo todos os Estacionamentos no arquivo.
     */
    public List<Estacionamento> getAll() {
        List<Estacionamento> estacionamentos = new ArrayList<>();
        Estacionamento estacionamento = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                estacionamento = (Estacionamento) inputFile.readObject();
                estacionamentos.add(estacionamento);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar estacionamento no disco!");
            e.printStackTrace();
        }
        return estacionamentos;
    }

    /**
     * Atualiza um objeto Estacionamento no arquivo.
     *
     * @param e O Estacionamento a ser atualizado.
     */
    public void update(Estacionamento e) {
        List<Estacionamento> estacionamentos = getAll();
        int index = estacionamentos.indexOf(e);
        if (index != -1) {
            estacionamentos.set(index, e);
        }
        saveToFile(estacionamentos);
    }

    /**
     * Remove um objeto Estacionamento do arquivo.
     *
     * @param e O Estacionamento a ser removido.
     */
    public void delete(Estacionamento e) {
        List<Estacionamento> estacionamentos = getAll();
        int index = estacionamentos.indexOf(e);
        if (index != -1) {
            estacionamentos.remove(index);
        }
        saveToFile(estacionamentos);
    }

    /**
     * Salva a lista atualizada de Estacionamentos no arquivo.
     *
     * @param estacionamentos A lista de Estacionamentos a ser salva.
     */
    public void saveToFile(List<Estacionamento> estacionamentos) {
        try {
            close();
            fos = new FileOutputStream(file, false); 
            outputFile = new ObjectOutputStream(fos);

            for (Estacionamento estacionamento : estacionamentos) {
                outputFile.writeObject(estacionamento);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar estacionamento no disco!");
            e.printStackTrace();
        }
    }
    
    /**
     * Fecha o fluxo de saída de dados após a utilização do EstacionamentoDAO.
     *
     * @throws IOException Se houver erro ao fechar os fluxos de saída de dados.
     */
    private void close() throws IOException {
        outputFile.close();
        fos.close();
    }

    /**
     * Método chamado automaticamente pelo Garbage Collector para fechar o EstacionamentoDAO.
     *
     * @throws Throwable Se houver erro ao finalizar o EstacionamentoDAO.
     */
    @Override
    protected void finalize() throws Throwable {
        this.close();
    }
}
