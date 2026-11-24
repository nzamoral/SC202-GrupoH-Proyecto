package proyecto;

import javax.swing.JOptionPane;

public class CARRITO {

    // Atributos
    private static final int MAXIMO_SERVICIOS = 10;

    private SERVICIO[] servicios;
    private int cantidadServicios;

    // Constructor
    public CARRITO() {
        this.servicios = new SERVICIO[MAXIMO_SERVICIOS];
        this.cantidadServicios = 0;
    }

    // Getters
    public int getCantidadServicios() {
        return cantidadServicios;
    }

    public SERVICIO getServicio(int posicion) {
        return servicios[posicion];
    }

    public boolean estaVacio() {
        return cantidadServicios == 0;
    }

    // Revisa si un servicio ya está en el carrito (se compara por nombre porque el id de SERVICIO es el de la categoría)
    public boolean contieneServicio(SERVICIO servicio) {
        boolean existe = false;

        for (int i = 0; i < cantidadServicios; i++) {
            if (servicios[i].getNombre().equals(servicio.getNombre())) {
                existe = true;
            }
        }

        return existe;
    }

    // Agrega un servicio al carrito. Retorna un mensaje para mostrarle al usuario
    public String agregarServicio(SERVICIO servicio) {
        String mensaje;

        if (contieneServicio(servicio)) {
            mensaje = "El servicio \"" + servicio.getNombre() + "\" ya está en su carrito.";
        } else if (cantidadServicios >= MAXIMO_SERVICIOS) {
            mensaje = "El carrito está lleno (máximo " + MAXIMO_SERVICIOS + " servicios).";
        } else {
            servicios[cantidadServicios] = servicio;
            cantidadServicios++;
            mensaje = "Servicio agregado al carrito:\n" + servicio.getNombre();
        }

        return mensaje;
    }

    // Elimina el servicio de la posición indicada (0 a cantidadServicios - 1) y corre los demás hacia la izquierda
    public void eliminarServicio(int posicion) {
        if (posicion >= 0 && posicion < cantidadServicios) {
            for (int i = posicion; i < cantidadServicios - 1; i++) {
                servicios[i] = servicios[i + 1];
            }
            servicios[cantidadServicios - 1] = null;
            cantidadServicios--;
        }
    }

    // Deja el carrito sin servicios
    public void vaciar() {
        for (int i = 0; i < cantidadServicios; i++) {
            servicios[i] = null;
        }
        cantidadServicios = 0;
    }

    // Suma el costo de todos los servicios (sin impuesto)
    public double calcularSubtotal() {
        double subtotal = 0;

        for (int i = 0; i < cantidadServicios; i++) {
            subtotal += servicios[i].getCosto();
        }

        return subtotal;
    }

    // Texto con el contenido del carrito
    public String mostrarCarrito() {
        String carritoString = "========== SU CARRITO ==========\n";

        if (estaVacio()) {
            carritoString += "\nEl carrito está vacío.";
        } else {
            for (int i = 0; i < cantidadServicios; i++) {
                carritoString += "\n" + (i + 1) + ". " + servicios[i].getNombre()
                        + "\n     " + servicios[i].mostrarInformacion();
            }
            carritoString += "\n\nSubtotal: ₡" + String.format("%.2f", calcularSubtotal());
        }

        return carritoString;
    }

    // Menú del carrito: permite ver, eliminar un servicio o vaciar el carrito
    public void gestionarCarrito() {
        boolean rep = true;

        while (rep) {
            if (estaVacio()) {
                JOptionPane.showMessageDialog(null, mostrarCarrito());
                rep = false;
            } else {
                String opcion = MENU.pedirDato(mostrarCarrito() + "\n\n"
                        + "1. Eliminar un servicio\n"
                        + "2. Vaciar carrito\n"
                        + "3. Volver al menú principal\n\n"
                        + "Digite el número de lo que desea seleccionar:");

                // Si el usuario cancela el diálogo, se vuelve al menú principal
                if (opcion == null) {
                    opcion = "3";
                }

                switch (opcion) {
                    case "1":
                        String posicion = MENU.pedirDato(mostrarCarrito()
                                + "\n\nDigite el número del servicio que desea eliminar:");
                        if (posicion != null) {
                            if (esNumeroEnRango(posicion)) {
                                eliminarServicio(Integer.parseInt(posicion) - 1);
                                JOptionPane.showMessageDialog(null, "Servicio eliminado del carrito.");
                            } else {
                                MENU.mostrarError();
                            }
                        }
                        break;
                    case "2":
                        vaciar();
                        JOptionPane.showMessageDialog(null, "El carrito quedó vacío.");
                        break;
                    case "3":
                        rep = false;
                        break;
                    default:
                        MENU.mostrarError();
                        break;
                }
            }
        }
    }

    // Valida que el texto sea un número entre 1 y la cantidad de servicios del carrito
    private boolean esNumeroEnRango(String texto) {
        boolean valido = false;

        if (texto.matches("[0-9]{1,2}")) {
            int numero = Integer.parseInt(texto);
            valido = numero >= 1 && numero <= cantidadServicios;
        }

        return valido;
    }

}
