import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaZonas {
    private List<Zona> zonasList;

    public ListaZonas(List<Zona> zonasList) {
        this.zonasList = zonasList;
    }

    public ListaZonas() {
        this.zonasList = new ArrayList<>();
    }

    public void showList(){
        for (Zona zona: zonasList) {
            zona.showInfo();
        }
    }

    public void leerDatos() {

        String linea;
        String seccion = null;
        Zona zona;

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

                if ("#Zona".equals(seccion)) {
                    String[] tokens = linea.split(";");
                    if (tokens.length >= 3) {
                        String identificacion = tokens[0];
                        String nombre = tokens[1];
                        String tipoReparto= tokens[2];

                        zona = new Zona(identificacion,nombre,tipoReparto);
                        zonasList.add(zona);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de errores, puedes personalizarlo según tus necesidades
        }
    }

    public int getLenght(){
        return zonasList.size();
    }

}
