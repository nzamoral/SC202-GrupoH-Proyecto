package proyecto;

public class SERVICIO {
    
    // Atributos
    private int id;
    private String categoria;
    private String nombre;
    private double costo;
    private ESPECIALISTA especialista; // Objeto de tipo ESPECIALISTA como atributo del servicio

    // Constructor
    public SERVICIO(int id, String categoria, String nombre, double costo, ESPECIALISTA especialista) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.costo = costo;
        this.especialista = especialista;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ESPECIALISTA getEspecialista() {
        return especialista;
    }

    public void setEspecialista(ESPECIALISTA especialista) {
        this.especialista = especialista;
    }

    // Mostrar información de servicio
    public String mostrarInformacion() {
        String servicioString = categoria + " | ₡" + costo + " | " + especialista.mostrarNombre();
        
        return servicioString;
    }
    
    // Mostrar línea de factura (FALTA IMPLEMENTACION)
    public void mostrarLineaFactura() {
        return;
    }
}
