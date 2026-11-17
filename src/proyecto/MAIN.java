package proyecto;

public class MAIN {

    public static void main(String[] args) {

        // Objetos que guardan los datos del programa mientras se ejecuta
        GESTORUSUARIOS gestorUsuarios = new GESTORUSUARIOS();

        // Llamada a método de la clase MENU para mostrar mensaje de bienvenida
        MENU.mostrarBienvenida();

        // Ciclo de autenticación. Este se repite hasta que el usuario inicie sesion (usuarioActivo no sea null) 
        // o hasta que falle 3 veces el inicio de sesion
        USUARIO usuarioActivo = null;
        boolean bloqueado = false;

        while (usuarioActivo == null && bloqueado == false) {
            String opcionAcceso = MENU.mostrarMenuAcceso();

            switch (opcionAcceso) {
                case "1":
                    usuarioActivo = gestorUsuarios.iniciarSesion();
                    if (usuarioActivo == null) {
                        bloqueado = true;
                    }
                    break;
                case "2":
                    gestorUsuarios.registrarUsuario();
                    break;
                default:
                    MENU.mostrarError();
                    break;
            }
        }

        // El menu principal solo se muestra si el inicio de sesion fue exitoso
        if (usuarioActivo != null) {
            boolean rep = true;

            while (rep) {
                String opcion = MENU.mostrarMenuPrincipal(usuarioActivo);

                switch (opcion) {
                    case "1":
                        // Seleccionar servicios
                        break;
                    case "2":
                        // Seleccionar horarios
                        break;
                    case "3":
                        // Mostrar carrito
                        break;
                    case "4":
                        // Mostrar factura
                        break;
                    case "5":
                        MENU.mostrarDespedida(usuarioActivo);
                        rep = false;
                        break;
                    default:
                        MENU.mostrarError();
                        break;
                }
            }
        }

    }

}
