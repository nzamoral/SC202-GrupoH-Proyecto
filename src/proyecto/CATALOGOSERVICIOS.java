package proyecto;

import javax.swing.JOptionPane;

public class CATALOGOSERVICIOS {

    // Atributos
    private SERVICIO[] servicios = new SERVICIO[10];   // Arreglo de tamaño fijo con los 10 servicios
    private int cantidadServicios = 0;                 // Contador de servicios cargados
    private String[] categorias = new String[5];       // Las 5 categorías del catálogo

    // Constructor: deja el catálogo listo para usarse apenas se crea
    public CATALOGOSERVICIOS() {
        cargarServicios();
    }

    // Crea los 5 especialistas y los 10 servicios (2 por categoría) y los guarda en los arreglos
    public void cargarServicios() {
        cantidadServicios = 0;

        // Categorías
        categorias[0] = "Faciales";
        categorias[1] = "Tratamientos corporales";
        categorias[2] = "Depilación láser";
        categorias[3] = "Manos y pies";
        categorias[4] = "Masajes";

        // Especialistas: uno por categoría
        ESPECIALISTA laura = new ESPECIALISTA("Laura", "Méndez", "Cosmetología facial");
        ESPECIALISTA carlos = new ESPECIALISTA("Carlos", "Vargas", "Terapia corporal");
        ESPECIALISTA daniela = new ESPECIALISTA("Daniela", "Rojas", "Depilación láser");
        ESPECIALISTA sofia = new ESPECIALISTA("Sofía", "Jiménez", "Manicura y pedicura");
        ESPECIALISTA mariana = new ESPECIALISTA("Mariana", "Quesada", "Masoterapia");

        // Servicios
        servicios[0] = new SERVICIO("F01", categorias[0], "Limpieza facial profunda", 18000, laura);
        servicios[1] = new SERVICIO("F02", categorias[0], "Radiofrecuencia", 35000, laura);
        servicios[2] = new SERVICIO("C01", categorias[1], "Drenaje linfático", 28000, carlos);
        servicios[3] = new SERVICIO("C02", categorias[1], "Masaje reductor", 32000, carlos);
        servicios[4] = new SERVICIO("D01", categorias[2], "Depilación de axilas", 20000, daniela);
        servicios[5] = new SERVICIO("D02", categorias[2], "Depilación de piernas completas", 45000, daniela);
        servicios[6] = new SERVICIO("M01", categorias[3], "Manicura semipermanente", 12000, sofia);
        servicios[7] = new SERVICIO("M02", categorias[3], "Pedicura spa", 15000, sofia);
        servicios[8] = new SERVICIO("R01", categorias[4], "Masaje relajante", 25000, mariana);
        servicios[9] = new SERVICIO("R02", categorias[4], "Masaje con piedras calientes", 30000, mariana);
        cantidadServicios = 10;
    }

    // Lee una opción. Si el usuario cierra o cancela el cuadro, MENU.pedirDato devuelve null
    // y eso tumbaría el programa al comparar, así que se toma como "0" (Regresar)
    private String leerOpcion(String mensaje) {
        String opcion = MENU.pedirDato(mensaje);
        if (opcion == null) {
            opcion = "0";
        }
        return opcion;
    }

    // Muestra las 5 categorías numeradas y devuelve la opción que digitó el usuario
    public String mostrarCategorias() {
        String texto = "========== CATEGORÍAS DE SERVICIOS ==========\n\n";
        for (int i = 0; i < categorias.length; i++) {
            texto += (i + 1) + ". " + categorias[i] + "\n";
        }
        texto += "0. Regresar al menú principal\n\n";
        texto += "Digite el número de la categoría que desea:";
        return leerOpcion(texto);
    }

    // Muestra los servicios de la categoría indicada, numerados desde 1,
    // y devuelve la opción que digitó el usuario
    public String mostrarServicios(String categoria) {
        String texto = "========== " + categoria + " ==========\n\n";
        int numero = 0;
        for (int i = 0; i < cantidadServicios; i++) {
            if (servicios[i].getCategoria().equals(categoria)) {
                numero++;
                texto += numero + ". " + servicios[i].mostrarInformacion() + "\n";
            }
        }
        texto += "0. Regresar a las categorías\n\n";
        texto += "Digite el número del servicio que desea agregar:";
        return leerOpcion(texto);
    }

    // Busca el servicio que corresponde al número que se le mostró al usuario dentro de una categoría
    // Devuelve null si el número no corresponde a ningún servicio
    private SERVICIO buscarServicioDeCategoria(String categoria, String opcion) {
        SERVICIO encontrado = null;
        int numero = 0;
        for (int i = 0; i < cantidadServicios; i++) {
            if (servicios[i].getCategoria().equals(categoria)) {
                numero++;
                if (opcion.equals("" + numero)) {
                    encontrado = servicios[i];
                }
            }
        }
        return encontrado;
    }

    // Pregunta si desea agregar otro servicio. Devuelve true si responde 1 (Sí) y false si responde 2 (No)
    private boolean preguntarOtroServicio() {
        boolean otro = false;
        while (true) {
            String respuesta = leerOpcion("¿Desea agregar otro servicio?\n1. Sí\n2. No");
            if (respuesta.equals("1")) {
                otro = true;
                break;
            } else if (respuesta.equals("2") || respuesta.equals("0")) {
                otro = false;
                break;
            } else {
                // Manejo de errores
                MENU.mostrarError();
            }
        }
        return otro;
    }

    // Opción 1 del menú principal: categorías -> servicios -> agregar al carrito -> ¿otro servicio?
    public void seleccionarServicios(CARRITO carrito) {
        boolean seguir = true;

        while (seguir) {
            String opcionCategoria = mostrarCategorias();

            if (opcionCategoria.equals("0")) {
                // Regresar al menú principal
                seguir = false;
            } else {
                // Se averigua cuál categoría eligió comparando el texto con cada número
                String categoria = null;
                for (int i = 0; i < categorias.length; i++) {
                    if (opcionCategoria.equals("" + (i + 1))) {
                        categoria = categorias[i];
                    }
                }

                if (categoria == null) {
                    // Manejo de errores
                    MENU.mostrarError();
                } else {
                    String opcionServicio = mostrarServicios(categoria);

                    if (opcionServicio.equals("0")) {
                        // Regresar: vuelve a mostrar las categorías
                    } else {
                        SERVICIO elegido = buscarServicioDeCategoria(categoria, opcionServicio);

                        if (elegido == null) {
                            // Manejo de errores
                            MENU.mostrarError();
                        } else {
                            boolean agregado = carrito.agregarServicio(elegido);

                            if (agregado == false) {
                                // Carrito lleno: no se puede seguir agregando, se vuelve al menú principal
                                JOptionPane.showMessageDialog(null,
                                        "El carrito está lleno. No es posible agregar más servicios.");
                                seguir = false;
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Servicio agregado al carrito:\n" + elegido.mostrarInformacion());
                                seguir = preguntarOtroServicio();
                            }
                        }
                    }
                }
            }
        }
    }

}
