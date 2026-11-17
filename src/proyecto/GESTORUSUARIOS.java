package proyecto;

import javax.swing.JOptionPane;

public class GESTORUSUARIOS {
    
    // Atributos
    private USUARIO[] usuarios;
    private int cantidadUsuarios;

    // Constructor modificado de la clase
    // Se encarga de instanciar la primera dimension del arreglo para guardar la información del admin
    public GESTORUSUARIOS() {
        this.usuarios = new USUARIO[10];
        // El sistema arranca con el usuario administrador ya registrado
        usuarios[0] = new USUARIO("Administrador", "", "00000000", "admin", "1234", "88888888");
        this.cantidadUsuarios = 1;
    }

    // Registra un usuario. Aplica validaciones sobre los datos ingresados, así como requerimientos específicos del nombre de usuario y contraseña
    public void registrarUsuario() {
        // Valida si el arreglo tiene espacio para agregar una nueva dimensión
        if (cantidadUsuarios == usuarios.length) {
            JOptionPane.showMessageDialog(null, "No hay espacio para nuevos registros.");
        } else {
            // Solicita datos de carácter obligatorio
            String nombre = MENU.pedirDatoObligatorio("Ingrese su nombre:");
            String apellido = MENU.pedirDatoObligatorio("Ingrese su apellido:");
            String cedula = MENU.pedirDatoObligatorio("Ingrese su cédula:");

            if (existeCedula(cedula)) {
                JOptionPane.showMessageDialog(null, "La cédula ingresada ya se encuentra registrada.");
            } else {
                String nombreUsuario;
                // Se implementa un ciclo while para asegurar que el usuario ingrese un nombre de usuario adecuado
                while (true) {
                    nombreUsuario = MENU.pedirDato("Ingrese su nombre de usuario (máximo 5 letras o números):");
                    // Realiza validaciones sobre el nombre de usuario
                    if (validarNombreUsuario(nombreUsuario) == false) { // Validación para asegurar que el nombre de usuario ingresado cumpla con los requerimientos solicitados
                        JOptionPane.showMessageDialog(null, "Error! El usuario debe tener de 1 a 5 caracteres, solo letras y números.");
                    } else if (existeNombreUsuario(nombreUsuario)) { // Validación para asegurar que el nombre de usuario ingresado no exista ya en el arreglo
                        JOptionPane.showMessageDialog(null, "Error! Ese nombre de usuario ya existe.");
                    } else {
                        break;
                    }
                }

                String contrasena;
                // Se implementa un ciclo while para asegurar que el usuario ingrese una contraseña adecuada
                while (true) {
                    contrasena = MENU.pedirDato("Ingrese su contraseña (máximo 4 caracteres):");
                    // Realiza validaciones sobre la contraseña
                    if (validarContrasena(contrasena)) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! La contraseña debe tener de 1 a 4 caracteres.");
                    }
                }

                // Solicita datos de carácter obligatorio
                String telefono = MENU.pedirDatoObligatorio("Ingrese su número de teléfono:");

                // Se registra un nuevo usuario con los datos colectados del usuario
                usuarios[cantidadUsuarios] = new USUARIO(nombre, apellido, cedula, nombreUsuario, contrasena, telefono);
                
                // Se incrementa el tracker interno para la cantidad de usuarios. 
                // Esto es importante porque se implementa en la validación de la cantidad máxima de dimensiones en el arreglo de usuarios
                cantidadUsuarios++;

                JOptionPane.showMessageDialog(null, "Registro exitoso. Por favor inicie sesión con sus credenciales.");
            }
        }
    }

    // Inicio de sesión
    // Retorna el usuario que inició sesión exitosamente o null si falló 3 veces
    public USUARIO iniciarSesion() {
        USUARIO usuarioEncontrado = null;
        int intentos = 0;

        while (usuarioEncontrado == null && intentos < 3) {
            String nombreUsuario = MENU.pedirDato("Nombre de usuario:");
            String contrasena = MENU.pedirDato("Contraseña:");

            usuarioEncontrado = buscarUsuario(nombreUsuario, contrasena);

            if (usuarioEncontrado == null) {
                intentos++;
                if (intentos < 3) {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Intentos restantes: " + (3 - intentos));
                } else {
                    JOptionPane.showMessageDialog(null, "Superó los 3 intentos permitidos. El programa se cerrará.");
                }
            }
        }
        return usuarioEncontrado;
    }

    // Recorre el arreglo buscando un usuario con ese nombre y contraseña
    public USUARIO buscarUsuario(String nombreUsuario, String contrasena) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getNombreUsuario().equals(nombreUsuario) && usuarios[i].getContrasena().equals(contrasena)) {
                return usuarios[i];
            }
        }
        return null;
    }

    // Validación sobre cédula existe
    public boolean existeCedula(String cedula) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    // Validación sobre nombre de usuario existente
    public boolean existeNombreUsuario(String nombreUsuario) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getNombreUsuario().equals(nombreUsuario)) {
                return true;
            }
        }
        return false;
    }

    // Validación sobre requisitos de cantidad de caracteres en nombre de usuario 
    public boolean validarNombreUsuario(String nombreUsuario) {
        if (nombreUsuario.length() < 1 || nombreUsuario.length() > 5) {
            return false;
        } else {
            return true;
        }
    }

    // Validación sobre requisitos de cantidad de caracteres en contraseña
    public boolean validarContrasena(String contrasena) {
        if (contrasena.length() >= 1 && contrasena.length() <= 4) {
            return true;
        } else {
            return false;
        }
    }

}
