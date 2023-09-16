



package fabrica;

class Material {
    private String nombre;
    private int cantidad;

    public Material(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
    //metodo para disminuir la cantidad
    public void disminuirCantidad(int cantidad) {
        if (this.cantidad >= cantidad) {
            this.cantidad -= cantidad;
        } else {
            System.out.println("No hay suficiente " + nombre + " en el inventario.");
        }
    }

    // Método para aumentar la cantidad de material
    public void aumentarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
}