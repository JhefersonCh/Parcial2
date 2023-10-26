public class Zona {
    private String identificacion;
    private String nombre;
    private String tipoReparto;

    public Zona(String identificacion, String nombre, String tipoReparto) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.tipoReparto = tipoReparto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoReparto() {
        return tipoReparto;
    }

    public void setTipoReparto(String tipoReparto) {
        this.tipoReparto = tipoReparto;
    }

    public void showInfo(){
        System.out.println("Identificacion: " + this.getIdentificacion());
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Tipo de reparto: " + this.getTipoReparto());
        System.out.println(" ");


    }
}
