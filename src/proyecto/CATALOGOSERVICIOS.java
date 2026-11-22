package proyecto;

import javax.swing.JOptionPane;

public class CATALOGOSERVICIOS {
    private SERVICIO[] servicios;
    private String[] categoriasServicios;
    private String[] nombresServicios;
    private double[] costosServicios;
    private ESPECIALISTA[] especialistasServicios;
    private int cantidadServiciosPorCategoria;
    
    // Constructor
    public CATALOGOSERVICIOS() {
        this.servicios = new SERVICIO[15];
        this.categoriasServicios = new String[] {
            "Cuidado facial", 
            "Masajes", 
            "Tratamiento capilar", 
            "Servicio de uñas", 
            "Depilación"
        };
        this.especialistasServicios = new ESPECIALISTA[] {
            new ESPECIALISTA("Andrés", "Vargas", "Estética facial"),
            new ESPECIALISTA("Valeria", "Mora", "Estética corporal"),
            new ESPECIALISTA("Mateo", "Solís", "Estética capilar"),
            new ESPECIALISTA("Laura", "Méndez", "Manicura"),
            new ESPECIALISTA("Camila", "Brenes", "Depilación")
        };
        this.nombresServicios = new String[] {
            // 0, 1, 2: Cuidado facial (Andrés Vargas)
            "Radiofrecuencia",
            "Limpieza facial profunda",
            "Peeling ultrasónico",

            // 3, 4, 5: Masajes (Valeria Mora)
            "Masaje relajante",
            "Masaje descontracturante",
            "Drenaje linfático",

            // 6, 7, 8: Tratamiento capilar (Mateo Solís)
            "Tratamiento anti-frizz",
            "Hidratación con keratina",
            "Ozonoterapia capilar",

            // 9, 10, 11: Servicio de uñas (Laura Méndez)
            "Pintura de uñas (manos)",
            "Uñas acrílicas esculpidas",
            "Pedicura spa completa",

            // 12, 13, 14: Depilación (Camila Brenes)
            "Depilación láser",
            "Depilación con cera (piernas)",
            "Diseño y depilación de cejas"
        };
        this.costosServicios = new double[] {
            // Cuidado facial
            35000, 28000, 32000,
            // Masajes
            25000, 32000, 38000,
            // Tratamiento capilar
            30000, 38000, 26000,
            // Servicio de uñas
            10000, 22000, 18000,
            // Depilación
            40000, 15000, 8000
        };
        this.cantidadServiciosPorCategoria = nombresServicios.length / categoriasServicios.length;
    }
    
    // Getters
    public String[] getCategoriasServicios() {
        return categoriasServicios;
    }
    
    public String[] getNombresServicios() {
        return nombresServicios;
    }
    
    // Cargar servicios con datos quemados
    public void cargarServicios() {
        for (int i = 0; i < categoriasServicios.length; i++) {
            for (int j = 0; j < cantidadServiciosPorCategoria; j++) {
                
                // Esta fórmula representa cada bloque de servicios agrupados por categoría
                int indiceServicio = (i * cantidadServiciosPorCategoria) + j;
                
                // Cada categoría tiene 3 servicios, entonces cantidadNombrePorCategoria = 3
                
                // Por ejemplo:
                // i = 0;   j = 1;   -->   (0 * 3) + 1 = 1
                
                servicios[indiceServicio] = new SERVICIO(
                        i + 1, 
                        categoriasServicios[i], 
                        nombresServicios[indiceServicio], 
                        costosServicios[indiceServicio], 
                        especialistasServicios[i]
                );
            }
        }
    }
    
    // Mostrar categorías
    public String mostrarCategorias() {
        String categoriasString = "\n========== CATEGORIAS DISPONIBLES ==========\n";
        
        for (int i = 0; i < categoriasServicios.length; i++) {
            categoriasString += "\n" + (i + 1) + ". " + categoriasServicios[i];
        }
        
        categoriasString += "\n\nDigite el número de lo que desea seleccionar:";
        
        return categoriasString;
    }
    
    // Mostrar servicios por categoría
    // Parámetro 'categoría' representa el índice de la categoría en
    // la variable 'categoriasServicios'.
    public String mostrarServicios(int indiceCategoria) {
        String serviciosString = "\n========== SERVICIOS DISPONIBLES ==========\n";
        
        // Loop de afuera corre solo una vez
        for (int i = indiceCategoria - 1; i < indiceCategoria; i++) {
            for (int j = 0; j < cantidadServiciosPorCategoria; j++) {
                int indiceServicio = (i * cantidadServiciosPorCategoria) + j;
            
                serviciosString += "\n" + (j + 1) + ". " + nombresServicios[indiceServicio];
            }
        }
        
        serviciosString += "\n\nDigite el número de lo que desea seleccionar:";
        
        return serviciosString;
    }
    
    // Seleccionar servicios (FALTA IMPLEMENTACIÓN PORQUE DEPENDE DE 'CARRITO')
    public void seleccionarServicios(CARRITO carrito) {
        // Código...
        return;
    }
}
