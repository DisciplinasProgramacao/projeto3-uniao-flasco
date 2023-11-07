package business.Cliente;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import business.Interfaces.DAO;

public class ClienteDAO implements DAO<Cliente, String> {
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public ClienteDAO(String filename) throws IOException {
        file = new File(filename);
        
        fos = new FileOutputStream(file, false);
        outputFile = new ObjectOutputStream(fos);
    }

    public void add(Cliente cliente) {
        try {
            outputFile.writeObject(cliente);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar o cliente com ID '" + cliente.getId() + "' no disco!");
            e.printStackTrace();
        }
    }

    public Cliente get(String id) {
        Cliente cliente = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                cliente = (Cliente) inputFile.readObject();

                if (id.equals(cliente.getId())) {
                    return cliente;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o cliente com ID '" + id + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                cliente = (Cliente) inputFile.readObject();
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar cliente no disco!");
            e.printStackTrace();
        }
        return clientes;
    }

    public void update(Cliente cliente) {
        List<Cliente> clientes = getAll();
        int index = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(cliente.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            clientes.set(index, cliente);
        }
        saveToFile(clientes);
    }

    public void delete(Cliente cliente) {
        List<Cliente> clientes = getAll();
        int index = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(cliente.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            clientes.remove(index);
        }
        saveToFile(clientes);
    }

    public void saveToFile(List<Cliente> clientes) {
        try {
            close();
            fos = new FileOutputStream(file, false);
            outputFile = new ObjectOutputStream(fos);

            for (Cliente cliente : clientes) {
                outputFile.writeObject(cliente);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar cliente no disco!");
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
