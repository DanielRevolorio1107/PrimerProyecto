


package fabrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


class RegistroInventario {
    private String archivo;

    public RegistroInventario(String archivo) {
        this.archivo = archivo;
    }

    public ArrayList<String> leerInventario() {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de inventario: " + e.getMessage());
        }
        return lineas;
    }
}