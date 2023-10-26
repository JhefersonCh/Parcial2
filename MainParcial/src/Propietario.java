import java.util.ArrayList;
import java.util.List;

public class Propietario {
    private String codigo;
    private String nombre;
    private String poblacion;
    private String email;
    private List<Propiedad> propiedades;

    public Propietario(String codigo,String nombre, String poblacion, String email, List<Propiedad> propiedades) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.email = email;
        this.propiedades = propiedades;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public void showInfo(){
        System.out.println("Codigo: " + this.getCodigo());
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Poblacion: " + this.getPoblacion());
        System.out.println("Email: " + this.getEmail());
        System.out.print("Propiedades: ");
        for(Propiedad propiedad: propiedades){
            System.out.print(propiedad.getCodPropiedad() + "  ");
        }
        System.out.println(" ");
        System.out.println(" ");
    }
}
