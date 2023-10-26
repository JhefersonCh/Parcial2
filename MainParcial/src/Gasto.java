public class Gasto {
    private String gasto;
    private String descripcion;
    private int importe;
    private String zona;

    public Gasto(String gasto, String descripcion, int importe, String zona) {
        this.gasto = gasto;
        this.descripcion = descripcion;
        this.importe = importe;
        this.zona = zona;
    }

    public String getGasto() {
        return gasto;
    }

    public void setGasto(String gasto) {
        this.gasto = gasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void showInfo(){
        System.out.println("Gasto: " + this.getGasto());
        System.out.println("Descripcion: " + this.getDescripcion());
        System.out.println("Importe: " + this.getImporte());
        System.out.println("Zona: " + this.getZona());
        System.out.println(" ");
    }
}
