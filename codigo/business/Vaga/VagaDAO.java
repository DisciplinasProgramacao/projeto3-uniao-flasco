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
            System.out.println("ERRO ao gravar a vaga  '" + vaga.getPlaca() + "' no disco!");
            e.printStackTrace();
        }
    }

    public Vaga get(String placa) {
        Vaga vaga = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                vaga = (Vaga) inputFile.readObject();

                if (placa.equals(vaga.getPlaca())) {
                    return vaga;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o veículo com placa '" + placa + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Vaga> getAll() {
        List<Vaga> veiculos = new ArrayList<Vaga>();
        Vaga vaga = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                vaga = (Vaga) inputFile.readObject();
                veiculos.add(vaga);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar veículo no disco!");
            e.printStackTrace();
        }
        return veiculos;
    }

    public void update(Vaga vaga) {
        List<Vaga> veiculos = getAll();
        int index = -1;
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(vaga.getPlaca())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            veiculos.set(index, vaga);
        }
        saveToFile(veiculos);
    }

    public void delete(Vaga vaga) {
        List<Vaga> veiculos = getAll();
        int index = -1;
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(vaga.getPlaca())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            veiculos.remove(index);
        }
        saveToFile(veiculos);
    }

    public void saveToFile(List<Vaga> veiculos) {
        try {
            close();
            fos = new FileOutputStream(file, false);
            outputFile = new ObjectOutputStream(fos);

            for (Vaga vaga : veiculos) {
                outputFile.writeObject(vaga);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar veículo no disco!");
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
