
package fabrica;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistroInventario registro = new RegistroInventario("inventario.txt");
        FabricaSillas fabrica = new FabricaSillas(registro);
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Fabricar Silla");
            System.out.println("2. Mostrar Sillas Fabricadas");
            System.out.println("3. Mostrar Inventario ");
            System.out.println("4. Orden de compra");
            System.out.println("5. clientes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Llamar al método para fabricar sillas
                    fabrica.fabricarSilla();
                   
                    break;
                case 2:
                    // Mostrar las sillas fabricadas
                    fabrica.mostrarSillasFabricadas();
                    break;
                case 3:
                    // Mostrar el inventario de materia prima
                    fabrica.mostrarInventario();
                    break;
                case 4:
                    // Gestionar pedidos con proveedores
                    System.out.println("Ingrese el nombre del proveedor");
                    String nombre = scanner.nextLine();
                    nombre = scanner.nextLine();
                    System.out.println("Ingrese el nit del proveedor");
                    int nit = scanner.nextInt();
                    fabrica.gestionarPedidosConProveedores();
                    break;
                case 5:
                    // Interacción con el cliente
                    Cliente cliente = crearCliente();
                    cliente.solicitarSillas(fabrica);
                    break;
                case 6:
                    // Salir del programa
                    System.out.println("Gracias por utilizar este programa");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

   private static Cliente crearCliente() {
    Scanner scanner = new Scanner(System.in);

    long cui;
    String nombre;
    String apellidos;
    String nit;

    System.out.print("Ingrese el CUI del cliente (solo números): ");
    while (true) {
        if (scanner.hasNextLong()) {
            cui = scanner.nextLong();
            break; // Salir del bucle si se ingresa un número
        } else {
            System.out.println("CUI no válido. Ingrese un número válido.");
            scanner.next(); // Limpiar el valor no válido del scanner
        }
    }

    scanner.nextLine(); // Consumir la nueva línea
    System.out.print("Ingrese el nombre del cliente: ");
    nombre = scanner.nextLine();
    System.out.print("Ingrese los apellidos del cliente: ");
    apellidos = scanner.nextLine();
    System.out.print("Ingrese el NIT del cliente: ");
    nit = scanner.next();

    return new Cliente((int) cui, nombre, apellidos, nit);
}

}