import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaComunidades {
    private List<Comunidad> comunidadesList;

    public ListaComunidades(List<Comunidad> comunidadesList) {
        this.comunidadesList = comunidadesList;
    }

    public ListaComunidades() {
        this.comunidadesList = new ArrayList<>();
    }

    public void showList(){
        for(Comunidad comunidad : comunidadesList){
            comunidad.showInfo();
        }
    }

    public void leerDatos() {

        String linea;
        String seccion = null;
        Comunidad comunidad;

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

                if ("#Comunidad".equals(seccion)) {
                    String[] tokens = linea.split(";");
                    if (tokens.length >= 3) {
                        String identificacion = tokens[0];
                        String nombre = tokens[1];
                        String poblacion= tokens[2];

                        comunidad = new Comunidad(identificacion,nombre,poblacion);
                        comunidadesList.add(comunidad);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de errores, puedes personalizarlo según tus necesidades
        }
    }

    public Comunidad getByCode(String code){
        int flag = 0;
        for(Comunidad comunidad : comunidadesList){
            if(comunidad.getIdentificacion().equals(code)){
                return comunidad;
            }
            break;
        }
        return null;
    }
}
