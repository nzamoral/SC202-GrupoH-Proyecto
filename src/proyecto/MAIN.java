package proyecto;

import javax.swing.JOptionPane;

public class MAIN {

    public static void main(String[] args) {

        // Objetos que guardan los datos del programa mientras se ejecuta
        GESTORUSUARIOS gestorUsuarios = new GESTORUSUARIOS();
        CATALOGOSERVICIOS catalogoServicios = new CATALOGOSERVICIOS();
        CARRITO carrito = new CARRITO();

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
            catalogoServicios.cargarServicios();  // Carga los servicios del sistema

            while (rep) {
                String opcion = MENU.mostrarMenuPrincipal(usuarioActivo);

                switch (opcion) {
                    case "1":
                        // Selección de categorías
                        String categoria;
                        int categoriaInt = 0;
                        // Selección de servicios ('servicioInt' NO USADO AUN)
                        String servicio;
                        int servicioInt = 0;
                        boolean repServicio1 = true;
                        boolean repServicio2 = true;
                        
                        String listaCategorias = catalogoServicios.mostrarCategorias();
                        
                        while (repServicio1) {
                            categoria = MENU.pedirDato(listaCategorias);
                            
                            // Validar si 'categoria' es un string numérico
                            switch (categoria) {
                                case "1", "2", "3", "4", "5":
                                    // Convertir 'categoria' a número entero (ver CATALOGOSERVICIO)
                                    categoriaInt = Integer.parseInt(categoria);
                                    repServicio1 = false;
                                    break;
                                default:
                                    MENU.mostrarError();
                            }
                        }
                        
                        String listaServicios = catalogoServicios.mostrarServicios(categoriaInt);
                        
                        while (repServicio2) {
                            servicio = MENU.pedirDato(listaServicios);
                            
                            // Validar si 'servicio' es un string numérico
                            switch (servicio) {
                                case "1", "2", "3":
                                    // Convertir 'servicio' a número entero (ver CATALOGOSERVICIO)
                                    servicioInt = Integer.parseInt(servicio);
                                    repServicio2 = false;
                                    break;
                                default:
                                    MENU.mostrarError();
                            }
                        }
                        
                        // Agrega el servicio elegido al carrito y avisa al usuario el resultado
                        SERVICIO servicioElegido = catalogoServicios.getServicio(categoriaInt, servicioInt);
                        JOptionPane.showMessageDialog(null, carrito.agregarServicio(servicioElegido));
                        
                        break;
                    case "2":
                        // Seleccionar horarios
                        break;
                    case "3":
                        // Mostrar carrito
                        carrito.gestionarCarrito();
                        break;
                    case "4":
                        // Mostrar factura
                        if (carrito.estaVacio()) {
                            JOptionPane.showMessageDialog(null, "Su carrito está vacío. Agregue servicios para generar la factura.");
                        } else {
                            FACTURA factura = new FACTURA(usuarioActivo, carrito);
                            factura.confirmarCompra();
                        }
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
