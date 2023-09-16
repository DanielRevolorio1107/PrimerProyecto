//listo
package fabrica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FabricaSillas {
    private Inventario inventario = new Inventario();
    private ArrayList<Silla> sillasFabricadas = new ArrayList<>();
    private RegistroInventario registro;
    private int cantidadSillasAFabricar;

    public FabricaSillas(RegistroInventario registro) {
        this.registro = registro;
        inventario.cargarInventarioDesdeArchivo(registro);
    }

    public void fabricarSilla() {
        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Ingrese el modelo de la silla: ");
        String modelo = scanner.next();


    int cantidadSillas = 0;
    boolean cantidadValida = false;
    while (!cantidadValida) {
        try {
            System.out.print("Ingrese la cantidad de sillas a fabricar: ");
            cantidadSillas = scanner.nextInt();
            cantidadValida = true;
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Ingrese un número entero válido.");
            scanner.nextLine(); // Consumir la entrada incorrecta
        }
    }
        
    // Definir los pasos del proceso
    String[] pasos = {
        "Diseño",
        "Extracción de materiales",
        "Corte de materiales",
        "Tapizado",
        "Pintura",
        "Empaque",
        "Envío a sala de venta"
    };          
      
        inventario.mostrarInventario();

         Map<String, Integer> materialesUtilizados = new HashMap<>();

        System.out.println("\nIngrese los materiales que desea utilizar para fabricar las sillas (o escriba 'listo' para finalizar):");

        while (true) {
            System.out.print("Nombre del material (o 'listo'): ");
            String nombreMaterial = scanner.next().toLowerCase();

            if (nombreMaterial.equals("listo")) {
                break;
            }else if (nombreMaterial.equals("siguiente")) {
            // Mostrar el siguiente paso del proceso
            mostrarSiguientePaso(pasos);
            continue;
        }

            System.out.print("Cantidad requerida: ");
            int cantidadMaterial = scanner.nextInt();

           Material material = inventario.obtenerMaterialPorNombreSimilar(nombreMaterial);

            
        if (material != null && material.getCantidad() >= cantidadMaterial) {
            materialesUtilizados.put(material.getNombre(), cantidadMaterial);
            material.disminuirCantidad(cantidadMaterial);
        } else {
            System.out.println("No hay suficiente " + nombreMaterial + " en el inventario.");
        }
    }
        
        
         // Verificar si se pueden fabricar sillas
    boolean sePuedenFabricarSillas = true;
    for (Map.Entry<String, Integer> entry : materialesUtilizados.entrySet()) {
        String nombreMaterial = entry.getKey();
        int cantidadMaterial = entry.getValue();
        Material material = inventario.obtenerMaterialPorNombreSimilar(nombreMaterial);
        if (material == null || material.getCantidad() < cantidadMaterial) {
            sePuedenFabricarSillas = false;
            break;
        }
    }

     if (sePuedenFabricarSillas) {
        System.out.println("\nComenzando la fabricación de " + cantidadSillas + " silla(s) de modelo '" + modelo + "'.");
        System.out.println("Proceso de fabricación:");

        // Simular el proceso de fabricación con los pasos
        for (String paso : pasos) {
            System.out.println("Paso: " + paso);
            System.out.print("Escriba 'siguiente' para continuar al siguiente paso: ");
            String entrada = scanner.next().toLowerCase();
            while (!entrada.equals("siguiente")) {
                System.out.print("Error: Debe ingresar 'siguiente' para avanzar al siguiente paso. Intente de nuevo: ");
                entrada = scanner.next().toLowerCase();
            }
        }
         // Crear la silla y agregarla a la lista de sillas fabricadas
        Silla silla = new Silla(modelo, cantidadSillas);
        sillasFabricadas.add(silla);

        // Actualizar el inventario después de fabricar las sillas
        inventario.actualizarInventarioArchivo();

        System.out.println("\nSilla(s) fabricada(s) con éxito.");
    } else {
        System.out.println("No se pudo fabricar la(s) silla(s) debido a la falta de materiales.");
    }
}
    
   private void mostrarSiguientePaso(String[] pasos) {
    Scanner scanner = new Scanner(System.in);
    int pasoActual = 0;

    while (pasoActual < pasos.length) {
        System.out.println("Paso: " + pasos[pasoActual]);
        System.out.print("Ingrese 'siguiente' para continuar al siguiente paso: ");
        String entrada = scanner.next().toLowerCase();

        if (entrada.equals("siguiente")) {
            pasoActual++;
        } else {
            System.out.println("Error: Debe ingresar 'siguiente' para avanzar al siguiente paso.");
        }
    }
}

    public void mostrarSillasFabricadas() {
        System.out.println("\nSillas fabricadas:");
        for (Silla silla : sillasFabricadas) {
            System.out.println(silla.getModelo() + ": " + silla.getCantidad());
        }
    }

    public void mostrarInventario() {
        inventario.mostrarInventario();
    }

    public boolean verificarDisponibilidadSillas(String modelo, int cantidadSillas) {
        // Verificar si se pueden fabricar las sillas
        int sillasDisponibles = obtenerCantidadSillasDisponibles(modelo);
        return sillasDisponibles >= cantidadSillas;
    }

    private int obtenerCantidadSillasDisponibles(String modelo) {
        int cantidadDisponibles = 0;
        for (Silla silla : sillasFabricadas) {
            if (silla.getModelo().equalsIgnoreCase(modelo)) {
                cantidadDisponibles += silla.getCantidad();
            }
        }
        return cantidadDisponibles;
    }

    public void generarFactura(Cliente cliente, String modelo, int cantidadSillas) {
        // Generar la factura con los datos del cliente y detalles de la compra
        System.out.println("\nFACTURA");
        System.out.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellidos());
        System.out.println("CUI: " + cliente.getCui());
        System.out.println("NIT: " + cliente.getNit());
        System.out.println("Silla(s) Comprada(s): " + modelo + " x" + cantidadSillas);
        System.out.println("Total a pagar: $" + calcularPrecio(modelo, cantidadSillas));
        System.out.println("¡Gracias por su compra!");
    }

    private double calcularPrecio(String modelo, int cantidadSillas) {
       //calculo de cantidad y precio
        return cantidadSillas * getPrecioSilla(modelo);
    }

    private double getPrecioSilla(String modelo) {
        
        return 100.0; // Ejemplo de precio
    }

    public void gestionarPedidosConProveedores() {
        Scanner scanner = new Scanner(System.in);

        // Mostrar el inventario antes de solicitar los detalles del pedido
        System.out.println("\n\t\t\tCATÁLOGO DE PRODUCTOS EXISTENTES");
        inventario.mostrarInventario();

        //  detalles del pedido
        System.out.println("\nGestión de Pedidos con Proveedores");

        Map<String, Integer> pedidosConProveedores = new HashMap<>();

        while (true) {
            System.out.print("Ingrese el nombre del material (o escriba 'listo' para finalizar): ");
            String nombreMaterial = scanner.next().toLowerCase();

            if (nombreMaterial.equals("listo")) {
                break;
            }

            System.out.print("Ingrese la cantidad requerida: ");
            int cantidadMaterial = scanner.nextInt();

            pedidosConProveedores.put(nombreMaterial, cantidadMaterial);
        }

        // Procesar los pedidos
        for (Map.Entry<String, Integer> pedido : pedidosConProveedores.entrySet()) {
            String nombreMaterial = pedido.getKey();
            int cantidadMaterial = pedido.getValue();

            // Verificar si los materiales  en el inventario
            Material materialExistente = inventario.obtenerMaterialPorNombreSimilar(nombreMaterial);

            if (materialExistente != null) {
               
                materialExistente.aumentarCantidad(cantidadMaterial);
            } else {
                // Informar al usuario si el material no existe en el inventario
                System.out.println("El material '" + nombreMaterial + "' no se encuentra en el inventario. No se pudo gestionar el pedido.");
            }
        }

        // Actualizar inventario
        inventario.actualizarInventarioArchivo();

        // Mostrar el inventario actualizado
        System.out.println("\nPedidos con proveedores gestionados con éxito. Inventario actualizado:");
    }
}
