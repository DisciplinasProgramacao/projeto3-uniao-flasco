package business.Vaga;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe VagaDAO fornece métodos para manipulação de arquivos relacionados às vagas de estacionamento.
 */
public class VagaDAO {
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;


    /**
     * Construtor que inicializa o VagaDAO com o nome do arquivo para operações de I/O.
     *
     * @param filename O nome do arquivo para armazenamento das informações das vagas.
     * @throws IOException Se ocorrer um erro de I/O ao tentar abrir o arquivo.
     */
    public VagaDAO(String filename) throws IOException {
        file = new File(filename);
       
        fos = new FileOutputStream(file, false);
        outputFile = new ObjectOutputStream(fos);
    }

     /**
     * Adiciona uma nova vaga ao arquivo.
     *
     * @param vaga A vaga a ser adicionada ao arquivo.
     */
    public void add(Vaga vaga) {
        try {
            outputFile.writeObject(vaga);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar a vaga  '" + vaga.getId() + "' no disco!");
            e.printStackTrace();
        }
    }

     /**
     * Obtém uma vaga específica do arquivo com base em seu ID.
     *
     * @param id O ID da vaga a ser recuperada.
     * @return A vaga correspondente ao ID fornecido.
     */
    public Vaga get(String id) {
        Vaga vaga = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                vaga = (Vaga) inputFile.readObject();

                if (id.equals(vaga.getId())) {
                    return vaga;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler a vaga com ID '" + id + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtém todas as vagas do arquivo.
     *
     * @return Uma lista contendo todas as vagas armazenadas no arquivo.
     */
    public List<Vaga> getAll() {
        List<Vaga> vagas = new ArrayList<Vaga>();
        Vaga vaga = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                vaga = (Vaga) inputFile.readObject();
                vagas.add(vaga);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar vaga no disco!");
            e.printStackTrace();
        }
        return vagas;
    }

    /**
     * Atualiza as informações de uma vaga no arquivo.
     *
     * @param vaga A vaga com as informações atualizadas.
     */
    public void update(Vaga vaga) {
        List<Vaga> vagas = getAll();
        int index = -1;
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).getId().equals(vaga.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            vagas.set(index, vaga);
        }
        saveToFile(vagas);
    }

    /**
     * Remove uma vaga do arquivo.
     *
     * @param vaga A vaga a ser removida do arquivo.
     */
    public void delete(Vaga vaga) {
        List<Vaga> vagas = getAll();
        int index = -1;
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).getId().equals(vaga.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            vagas.remove(index);
        }
        saveToFile(vagas);
    }


    /**
     * Salva a lista de vagas no arquivo.
     *
     * @param vagas A lista de vagas a ser salva no arquivo.
     */
    public void saveToFile(List<Vaga> vagas) {
        try {
            close();
            fos = new FileOutputStream(file, false);
            outputFile = new ObjectOutputStream(fos);

            for (Vaga vaga : vagas) {
                outputFile.writeObject(vaga);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar vaga no disco!");
            e.printStackTrace();
        }
    }

    /**
     * Fecha os fluxos de saída de arquivo.
     *
     * @throws IOException Se ocorrer um erro ao fechar os fluxos de arquivo.
     */
    public void close() throws IOException {
        outputFile.close();
        fos.close();
    }

    /**
     * Método invocado pelo garbage collector para garantir que os recursos do arquivo sejam liberados.
     *
     * @throws Throwable Se ocorrer um erro durante o processo de finalização.
     */
    @Override
    protected void finalize() throws Throwable {
        this.close();
    }
}
