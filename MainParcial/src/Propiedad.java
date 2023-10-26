import java.util.List;

public class Propiedad {

    private String tipo;
    private String codPropiedad;
    private int metros2;
    private String codPropietario;
    private List<Porcentajes> listaPorcentajes;
    private String tipoEspecifico;
    private String detalleEspecifico;

    public Propiedad(String tipo, String codPropiedad, int metros2, String codPropietario, List<Porcentajes> listaPorcentajes, String tipoEspecifico, String detalleEspecifico) {
        this.tipo = tipo;
        this.codPropiedad = codPropiedad;
        this.metros2 = metros2;
        this.codPropietario = codPropietario;
        this.listaPorcentajes = listaPorcentajes;
        this.tipoEspecifico = tipoEspecifico;
        this.detalleEspecifico = detalleEspecifico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodPropiedad() {
        return codPropiedad;
    }

    public void setCodPropiedad(String codPropiedad) {
        this.codPropiedad = codPropiedad;
    }

    public int getMetros2() {
        return metros2;
    }

    public void setMetros2(int metros2) {
        this.metros2 = metros2;
    }

    public String getCodPropietario() {
        return codPropietario;
    }

    public void setCodPropietario(String codPropietario) {
        this.codPropietario = codPropietario;
    }

    public List<Porcentajes> getListaPorcentajes() {
        return listaPorcentajes;
    }

    public void setListaPorcentajes(List<Porcentajes> listaPorcentajes) {
        this.listaPorcentajes = listaPorcentajes;
    }

    public String getTipoEspecifico() {
        return tipoEspecifico;
    }

    public void setTipoEspecifico(String tipoEspecifico) {
        this.tipoEspecifico = tipoEspecifico;
    }

    public String getDetalleEspecifico() {
        return detalleEspecifico;
    }

    public void setDetalleEspecifico(String detalleEspecifico) {
        this.detalleEspecifico = detalleEspecifico;
    }

    public void showInfo(){
        System.out.println("Tipo de propiedad: " + this.getTipo());
        System.out.println("Codigo de propiedad: " + this.getCodPropiedad());
        System.out.println("Metros cuadrados: " + this.getMetros2());
        System.out.println("Codigo de propietario: " + this.getCodPropiedad());
        System.out.print("Porcentajes: ");
        for(Porcentajes porcentaje : this.getListaPorcentajes()){
            System.out.print(porcentaje.getPorciento() + "%"  + "  ");
        }
        System.out.println("");
        System.out.println("Tipo especifico: " + this.getTipoEspecifico());
        System.out.println("Detalle especifico: " + this.getDetalleEspecifico());
        System.out.println(" ");
    }

}
