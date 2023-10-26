import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaGastos {
    private List<Gasto> gastosList;

    public ListaGastos(List<Gasto> gastosList) {
        this.gastosList = gastosList;
    }

    public ListaGastos() {
        this.gastosList = new ArrayList<>();
    }

    public void addGasto(Gasto gasto){
        this.gastosList.add(gasto);
    }

    public void showList(){
        for(Gasto gasto: gastosList){
            gasto.showInfo();
        }
    }

    public void leerDatos() {

        String linea;
        String seccion = null;
        Gasto gasto;

        try (BufferedReader br = new BufferedReader(new FileReader("Gastos.txt"))) {
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue; // Ignorar líneas en blanco
                }

                if (linea.startsWith("#")) {
                    seccion = linea;
                    continue; // Saltar la línea de sección
                }

                if ("#Gastos 2008".equals(seccion)) {
                    String[] tokens = linea.split(";");
                    if (tokens.length >= 4) {
                        String gast = tokens[0];
                        String descripcion = tokens[1];
                        String importe = tokens[2];
                        String zona= tokens[3];

                        gasto = new Gasto(gast,descripcion,Utils.stringtoint(importe),zona);
                        gastosList.add(gasto);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de errores, puedes personalizarlo según tus necesidades
        }
    }
    public int getLenght(){
        return gastosList.size();
    }

    public List<Gasto> getGastosList() {
        return gastosList;
    }

    public void setGastosList(List<Gasto> gastosList) {
        this.gastosList = gastosList;
    }
}
