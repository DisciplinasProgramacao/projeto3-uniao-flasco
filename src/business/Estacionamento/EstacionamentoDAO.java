package business.Estacionamento;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import business.Interfaces.DAO;

/**
 * Implementação da interface DAO para manipulação de objetos Estacionamento.
 * Realiza operações de leitura e escrita em arquivo para armazenar instâncias de Estacionamento.
 */
public class EstacionamentoDAO implements DAO<Estacionamento, Integer> {
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

     /**
     * Inicializa o EstacionamentoDAO com o nome do arquivo onde os dados serão armazenados.
     *
     * @param filename O nome do arquivo de armazenamento.
     * @throws IOException Se houver erro na criação ou acesso ao arquivo.
     */
    public EstacionamentoDAO(String filename) throws IOException {
        file = new File(filename);
      
        fos = new FileOutputStream(file, true);
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
            System.out.println("ERRO ao gravar o estacionamento com ID '" + estacionamento.getId() + "' no disco!");
            e.printStackTrace();
        }
    }

     /**
     * Retorna um objeto Estacionamento com base no ID fornecido.
     *
     * @param id O ID do Estacionamento a ser recuperado.
     * @return O Estacionamento com o ID correspondente ou null se não encontrado.
     */
    public Estacionamento get(Integer id) {
        Estacionamento estacionamento = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                estacionamento = (Estacionamento) inputFile.readObject();

                if (id.equals(estacionamento.getId())) {
                    return estacionamento;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o estacionamento com ID '" + id + "' do disco!");
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
        List<Estacionamento> estacionamentos = new ArrayList<Estacionamento>();
        Estacionamento estacionamento = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                estacionamento = (Estacionamento) inputFile.readObject();
                estacionamentos.add(estacionamento);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao dar getALL!");
            e.printStackTrace();
        }
        return estacionamentos;
    }

    /**
     * Atualiza um objeto Estacionamento no arquivo.
     *
     * @param estacionamento O Estacionamento a ser atualizado.
     */
    public void update(Estacionamento estacionamento) {
        List<Estacionamento> estacionamentos = getAll();
        int index = -1;
        for (int i = 0; i < estacionamentos.size(); i++) {
            if (estacionamentos.get(i).getId() == estacionamento.getId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            estacionamentos.set(index, estacionamento);
        }
        saveToFile(estacionamentos);
    }

    /**
     * Remove um objeto Estacionamento do arquivo.
     *
     * @param estacionamento O Estacionamento a ser removido.
     */
    public void delete(Estacionamento estacionamento) {
        List<Estacionamento> estacionamentos = getAll();
        int index = -1;
        for (int i = 0; i < estacionamentos.size(); i++) {
            if (estacionamentos.get(i).getId() == estacionamento.getId()) {
                index = i;
                break;
            }
        }

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
        try (FileOutputStream fos = new FileOutputStream(file, false);
             ObjectOutputStream outputFile = new ObjectOutputStream(fos)) {
            for (Estacionamento est : estacionamentos) {
                outputFile.writeObject(est);
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
    public void close() throws IOException {
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
