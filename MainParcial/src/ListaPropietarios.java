import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaPropietarios {
    private List<Propietario> propietariosList;

    public ListaPropietarios(List<Propietario> propietariosList) {
        this.propietariosList = propietariosList;
    }

    public ListaPropietarios() {
        this.propietariosList = new ArrayList<>();
    }

    public void showList(){
        for(Propietario propietario: propietariosList){
            propietario.showInfo();
        }
    }

    public void leerDatos(ListaPropiedades propiedades) {

        String linea;
        String seccion = null;
        Propietario propietario;

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

                if ("#Propietario".equals(seccion)) {
                    String[] tokens = linea.split(";");
                    if (tokens.length >= 4) {
                        String codigo = tokens[0];
                        String nombre = tokens[1];
                        String poblacion = tokens[2];
                        String email= tokens[3];
                        List <Propiedad> arrayPropiedades = new ArrayList<>();
                        for(Propiedad propiedad: propiedades.getPropiedadesList()){
                            if(propiedad.getCodPropietario().equals(codigo)){
                                arrayPropiedades.add(propiedad);
                            }
                        }

                        propietario = new Propietario(codigo,nombre,poblacion,email, arrayPropiedades);
                        propietariosList.add(propietario);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de errores, puedes personalizarlo según tus necesidades
        }
    }
    public int getLenght(){
        return propietariosList.size();
    }

    public List<Propietario> getPropietariosList() {
        return propietariosList;
    }

    public void setPropietariosList(List<Propietario> propietariosList) {
        this.propietariosList = propietariosList;
    }
}
