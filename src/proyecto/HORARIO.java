package proyecto;

import javax.swing.JOptionPane;

public class HORARIO {

    // Atributos
    private String[] dias;
    private String[] horas;
    private boolean[][] ocupado;   // ocupado[dia][hora]: true si ese espacio ya está reservado
    private int diaElegido;        // -1 mientras el usuario no haya elegido horario
    private int horaElegida;

    // Constructor
    public HORARIO() {
        this.dias = new String[] {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        this.horas = new String[] {"9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"};
        this.ocupado = new boolean[dias.length][horas.length];
        this.diaElegido = -1;
        this.horaElegida = -1;

        // Espacios ya reservados por otros clientes (datos quemados)
        ocupado[0][1] = true;
        ocupado[1][0] = true;
        ocupado[2][3] = true;
        ocupado[2][4] = true;
        ocupado[4][2] = true;
        ocupado[5][0] = true;
        ocupado[5][1] = true;
    }

    // Retorna true si el usuario ya eligió día y hora
    public boolean estaDefinido() {
        return diaElegido != -1 && horaElegida != -1;
    }

    // Texto de la cita elegida, para mostrar en la factura
    public String mostrarCita() {
        String citaString = "Sin horario seleccionado";

        if (estaDefinido()) {
            citaString = dias[diaElegido] + " a las " + horas[horaElegida];
        }

        return citaString;
    }

    // Lista de días
    public String mostrarDias() {
        String diasString = "========== DÍAS DISPONIBLES ==========\n";

        for (int i = 0; i < dias.length; i++) {
            diasString += "\n" + (i + 1) + ". " + dias[i];
        }

        diasString += "\n\nDigite el número del día que desea:";

        return diasString;
    }

    // Lista de horas del día indicado, marcando las que ya están reservadas
    public String mostrarHoras(int dia) {
        String horasString = "========== HORAS PARA EL " + dias[dia].toUpperCase() + " ==========\n";

        for (int j = 0; j < horas.length; j++) {
            horasString += "\n" + (j + 1) + ". " + horas[j];
            if (ocupado[dia][j]) {
                horasString += "  (reservada)";
            }
        }

        horasString += "\n\nDigite el número de la hora que desea:";

        return horasString;
    }

    // Pide al usuario el día y la hora de su cita. Retorna true si quedó elegido un horario
    public boolean seleccionarHorario() {
        boolean elegido = false;
        int dia = pedirNumero(mostrarDias(), dias.length);

        if (dia != -1) {
            boolean rep = true;

            while (rep) {
                int hora = pedirNumero(mostrarHoras(dia - 1), horas.length);

                if (hora == -1) {
                    rep = false;   // el usuario canceló
                } else if (ocupado[dia - 1][hora - 1]) {
                    JOptionPane.showMessageDialog(null, "Esa hora ya está reservada, elija otra.");
                } else {
                    diaElegido = dia - 1;
                    horaElegida = hora - 1;
                    elegido = true;
                    rep = false;
                    JOptionPane.showMessageDialog(null, "Horario seleccionado:\n" + mostrarCita());
                }
            }
        }

        return elegido;
    }

    // Se llama al confirmar la compra: la hora elegida queda reservada y el usuario puede elegir otra cita
    public void confirmarReserva() {
        if (estaDefinido()) {
            ocupado[diaElegido][horaElegida] = true;
        }
        diaElegido = -1;
        horaElegida = -1;
    }

    // Pide un número entre 1 y 'maximo'. Retorna -1 si el usuario cancela
    private int pedirNumero(String mensaje, int maximo) {
        int numero = -1;
        boolean rep = true;

        while (rep) {
            String dato = MENU.pedirDato(mensaje);

            if (dato == null) {
                rep = false;
            } else if (dato.matches("[0-9]{1,2}") && Integer.parseInt(dato) >= 1 && Integer.parseInt(dato) <= maximo) {
                numero = Integer.parseInt(dato);
                rep = false;
            } else {
                MENU.mostrarError();
            }
        }

        return numero;
    }

}
