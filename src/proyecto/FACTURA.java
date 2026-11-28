package proyecto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class FACTURA {

    // Atributos
    private static final double IMPUESTO_VENTAS = 0.13;
    private static int contadorFacturas = 0;

    private int numero;
    private String fecha;
    private USUARIO usuario;
    private CARRITO carrito;
    private HORARIO horario;

    // Constructor
    public FACTURA(USUARIO usuario, CARRITO carrito, HORARIO horario) {
        contadorFacturas++;
        this.numero = contadorFacturas;
        this.fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.usuario = usuario;
        this.carrito = carrito;
        this.horario = horario;
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public double calcularSubtotal() {
        return carrito.calcularSubtotal();
    }

    public double calcularImpuesto() {
        return calcularSubtotal() * IMPUESTO_VENTAS;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularImpuesto();
    }

    // Texto completo de la factura
    public String mostrarFactura() {
        String facturaString = "========== " + MENU.obtenerNombreApp() + " ==========\n"
                + "FACTURA #" + String.format("%04d", numero) + "\n"
                + "Fecha: " + fecha + "\n"
                + "Cliente: " + usuario.getNombreCompleto() + "\n"
                + "Cédula: " + usuario.getCedula() + "\n"
                + "Teléfono: " + usuario.getTelefono() + "\n"
                + "Cita: " + horario.mostrarCita() + "\n"
                + "------------------------------------------\n";

        for (int i = 0; i < carrito.getCantidadServicios(); i++) {
            facturaString += carrito.getServicio(i).mostrarLineaFactura() + "\n";
        }

        facturaString += "------------------------------------------\n"
                + "Subtotal: ₡" + String.format("%.2f", calcularSubtotal()) + "\n"
                + "IVA (13%): ₡" + String.format("%.2f", calcularImpuesto()) + "\n"
                + "TOTAL: ₡" + String.format("%.2f", calcularTotal());

        return facturaString;
    }

    // Muestra la factura y pregunta si se confirma la compra. Si se confirma, el carrito queda vacío
    // Retorna true si la compra fue confirmada
    public boolean confirmarCompra() {
        boolean confirmada = false;
        boolean rep = true;

        while (rep) {
            String opcion = MENU.pedirDato(mostrarFactura() + "\n\n"
                    + "1. Confirmar compra\n"
                    + "2. Volver al menú principal\n\n"
                    + "Digite el número de lo que desea seleccionar:");

            // Si el usuario cancela el diálogo, se vuelve al menú principal
            if (opcion == null) {
                opcion = "2";
            }

            switch (opcion) {
                case "1":
                    confirmada = true;
                    rep = false;
                    carrito.vaciar();
                    horario.confirmarReserva();
                    JOptionPane.showMessageDialog(null, "¡Compra confirmada! Factura #" + String.format("%04d", numero)
                            + "\nGracias por preferir " + MENU.obtenerNombreApp() + ".");
                    break;
                case "2":
                    rep = false;
                    break;
                default:
                    MENU.mostrarError();
                    break;
            }
        }

        return confirmada;
    }

}
