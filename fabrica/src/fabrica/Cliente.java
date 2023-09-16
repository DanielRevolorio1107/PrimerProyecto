//listo
package fabrica;

import java.util.Scanner;

public class Cliente {
    private int cui;
    private String nombre;
    private String apellidos;
    private String nit;

    public Cliente(int cui, String nombre, String apellidos, String nit) {
        this.cui = cui;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nit = nit;
    }

    public int getCui() {
        return cui;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNit() {
        return nit;
    }

    public void solicitarSillas(FabricaSillas fabrica) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el modelo de la silla
        System.out.print("Ingrese el modelo de la silla que desea comprar: ");
        String modelo = scanner.next();

        // Solicitar la cantidad de sillas a comprar
        System.out.print("Ingrese la cantidad que desea comprar: ");
        int cantidadSillas = scanner.nextInt();

        //verificar las sillas del inventario
        boolean disponibles = fabrica.verificarDisponibilidadSillas(modelo, cantidadSillas);

        if (disponibles) {
            System.out.println("Entrega realizada con éxito.");
            System.out.print("¿Desea factura? (Sí/No): ");
            String deseaFactura = scanner.next();
            if (deseaFactura.equalsIgnoreCase("Sí")) {
                  
                fabrica.generarFactura(this, modelo, cantidadSillas);
                
            }
        } else {
            System.out.println("No hay suficientes sillas disponibles en existencia.");
        }
    }
}
