package proyecto;

import javax.swing.JOptionPane;

public class MENU {

    // Retorna el nombre de la aplicación. Se usa en diferentes salidas del programa
    public static String obtenerNombreApp() {
        return "Alondra Beauty and Spa";
    }

    // Pide un dato al usuario
    // Este método es utilizado por varios métodos que solicitan una opción al usuario, como mostrarMenuAcceso() y mostrarMenuPrincipal()
    public static String pedirDato(String mensaje) {
        String dato = JOptionPane.showInputDialog(mensaje);
        return dato;
    }

    // Pide un dato que no puede quedar vacio. Por ejemplo nombres, apellidos, números telefónicos
    // Reutiliza la implementacion de pedirDatos() y agrega la validación de que el datos no puede ser un texto vacio
    public static String pedirDatoObligatorio(String mensaje) {
        String dato;
        while (true) {
            dato = pedirDato(mensaje);
            if (dato.equals("")) {
                JOptionPane.showMessageDialog(null, "Error! Este dato es obligatorio.");
            } else {
                break;
            }
        }
        return dato;
    }

    // Muestra mensaje de bienvenida al usuario
    public static void mostrarBienvenida() {
        JOptionPane.showMessageDialog(null,
                "==========================================\n" +
                "   Bienvenido(a) a " + obtenerNombreApp() + "\n" +
                "   Clínica de Servicios Estéticos\n" +
                "==========================================");
    }

    // Muestra menú inicial al usuario, donde puede iniciar sesión o registrarse en el sistema
    public static String mostrarMenuAcceso() {
        String menu = obtenerNombreApp() + "\n" +
                "1. Iniciar sesión\n" +
                "2. Registrarse\n" +
                "Digite el número de la opción que desea:";
        return pedirDato(menu);
    }

    // Muestra el menú principal del programa una vez autenticado el usuario
    // El usuario autenticado se convierte en el usuario activo dentro de la sesión y esto se usa para personalizar las impresiones de diferentes menus y despedidas
    public static String mostrarMenuPrincipal(USUARIO usuario) {
        String menu = "========== " + obtenerNombreApp() + " ==========\n" +
                "Usuario: " + usuario.getNombreCompleto() + "\n" + "\n" +
                "1. Servicios\n" +
                "2. Horario\n" +
                "3. Carrito\n" +
                "4. Facturación\n" +
                "5. Salir\n" + "\n" +
                "Digite el número de lo que desea seleccionar:";
        return pedirDato(menu);
    }

    // Muestra un mensaje de despedida al usuario y utiliza métodos como obtenerNombreApp() y getNombreCompleto() para personalizar el mensaje
    public static void mostrarDespedida(USUARIO usuario) {
        JOptionPane.showMessageDialog(null,
                "Gracias por preferir " + obtenerNombreApp() + ", " + usuario.getNombreCompleto() + ".\n" +
                "¡Le esperamos pronto!");
    }

    // Muestra un mensaje de error cuando el usuario ingresa una opción incorrecta. Se retutiliza en diferentes partes del programa
    public static void mostrarError() {
        JOptionPane.showMessageDialog(null, "Opción no válida, intente nuevamente.");
    }

}
