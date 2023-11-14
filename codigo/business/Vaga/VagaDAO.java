package business.Vaga;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class VagaDAO {
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public VagaDAO(String filename) throws IOException {
        file = new File(filename);
       
        fos = new FileOutputStream(file, false);
        outputFile = new ObjectOutputStream(fos);
    }

    public void add(Vaga vaga) {
        try {
            outputFile.writeObject(vaga);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar a vaga  '" + vaga.getId() + "' no disco!");
            e.printStackTrace();
        }
    }

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

    public void close() throws IOException {
        outputFile.close();
        fos.close();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }
}