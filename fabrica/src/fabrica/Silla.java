
    package fabrica;



     class Silla {
        private String modelo;
        private int cantidad;

        public Silla(String modelo, int cantidad) {
            this.modelo = modelo;
            this.cantidad = cantidad;
        }

        public String getModelo() {
            return modelo;
        }

        public int getCantidad() {
            return cantidad;
        }
    }