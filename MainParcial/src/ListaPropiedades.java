import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPropiedades {
    private List<Propiedad> propiedadesList;

    public ListaPropiedades(List<Propiedad> propiedades) {
        this.propiedadesList = propiedades;
    }

    public ListaPropiedades() {
        this.propiedadesList = new ArrayList<>();
    }

    public void addPropiedad(Propiedad propiedad){
        propiedadesList.add(propiedad);
    }

    public void showList(){
        for(Propiedad propiedad: propiedadesList){
            propiedad.showInfo();
        }
    }

    public void leerDatos() {

        String linea;
        String seccion = null;
        Propiedad propiedad;

        try (BufferedReader br = new BufferedReader(new FileReader("comunidad.txt"))) {
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue; // Ignorar líneas en blanco
                }

                if (linea.startsWith("#")) {
                    seccion = linea;
                    continue; // Saltar la línea de sección
                }

                if ("#Propiedad".equals(seccion)) {
                    String[] tokens = linea.split(";");
                    if (tokens.length >= 6) {
                        String tipo = tokens[0];
                        String codPropiedad = tokens[1];
                        String metros2 = tokens[2];
                        String codPropietario = tokens[3];
                        String[] porcentajesZonas = tokens[4].split(",");
                        String tipoEspecifico = tokens[5];
                        String detalleEspecifico = tokens[6];
                        List<Porcentajes> porcentajes = new ArrayList<>();
                        for(String porcentaje : porcentajesZonas){
                            String[] partes = porcentaje.split("-");
                            String codigo = partes[0];
                            int porciento = Utils.stringtoint(partes[1]);
                            Porcentajes porcentaj = new Porcentajes(codigo,porciento);
                            porcentajes.add(porcentaj);
                        }

                        propiedad = new Propiedad(tipo, codPropiedad, Utils.stringtoint(metros2), codPropietario, porcentajes, tipoEspecifico, detalleEspecifico);
                        //this.LArticulo.add(articulo);
                        propiedadesList.add(propiedad);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de errores, puedes personalizarlo según tus necesidades
        }
    }

    public int getLenght(){
        return propiedadesList.size();
    }

    public List<Propiedad> getPropiedadesList() {
        return propiedadesList;
    }

    public void setPropiedadesList(List<Propiedad> propiedadesList) {
        this.propiedadesList = propiedadesList;
    }
}
