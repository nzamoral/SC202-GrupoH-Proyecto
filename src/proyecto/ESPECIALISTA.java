package proyecto;

public class ESPECIALISTA {
    
    // Atributos
    private String nombre;
    private String apellido;
    private String especialidad;

    // Constructor
    public ESPECIALISTA(String nombre, String apellido, String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // Devuelve el nombre listo para mostrar en pantalla, por ejemplo "Esp. Laura Méndez"
    // Lo usa SERVICIO dentro de mostrarInformacion() y mostrarLineaFactura()
    public String mostrarNombre() {
        return "Esp. " + nombre + " " + apellido;
    }

}
