

//listo

package fabrica;
//listo
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

    class Inventario {
        private LinkedHashMap<String, Material> materiales = new LinkedHashMap<>();
        
        public void cargarInventarioDesdeArchivo(RegistroInventario registro) {
            ArrayList<String> lineas = registro.leerInventario();
            for (String linea : lineas) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    int cantidad = Integer.parseInt(partes[1].trim());
                    agregarMaterial(new Material(nombre, cantidad));
                }
            }
        }
        //metodo para mostrar inventario al usuario
      public void mostrarInventario() {
    System.out.println("\t\t\t\tINVENTARIO:\n");
    System.out.println("DESCRIPCION.......................................................UNIDADES");

    ArrayList<String> lineas = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("inventario.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            lineas.add(linea);
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo de inventario: " + e.getMessage());
    }

    // Mostrar las líneas del archivo en el mismo orden
    for (String linea : lineas) {
        String[] partes = linea.split(":");
        if (partes.length == 2) {
            String nombre = partes[0].trim();
            int cantidad = Integer.parseInt(partes[1].trim());
            System.out.println(nombre + ": " + cantidad);
        }
    }
}       
      //metodo para agregar material al inventario
        public void agregarMaterial(Material material) {
            materiales.put(material.getNombre(), material);
        }

      public Material obtenerMaterialPorNombreSimilar(String nombre) {
    // Buscar el material de manera similar al nombre ingresado
    for (Material material : materiales.values()) {
        if (material.getNombre().toLowerCase().contains(nombre.toLowerCase()) || nombre.equalsIgnoreCase("no")) {
            return material;
        }
    }
    return null;
}
      //mostrar un nuevo inventario
      public void actualizarInventarioArchivo() {
    try (PrintWriter writer = new PrintWriter(new FileWriter("inventario.txt"))) {
        for (Material material : materiales.values()) {
            writer.println(material.getNombre() + ": " + material.getCantidad());
        }
    } catch (IOException e) {
        System.err.println("Error al actualizar el archivo de inventario: " + e.getMessage());
    }
}
    }

