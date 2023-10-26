import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SalidasTXT {
    public void resumen(ListaComunidades comunidades, ListaPropietarios propietarios, ListaZonas zonas, ListaPropiedades propiedades, ListaGastos gastos){
        String nombreArchivo = "resumen.txt";
        Comunidad comunidad;
        comunidad = comunidades.getByCode("01");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

            writer.write("ESTADISTICAS: ");
            writer.newLine();
            writer.newLine();
            writer.newLine();
            writer.write("Comunidad: " + comunidad.getIdentificacion() + " " + comunidad.getNombre());
            writer.newLine();
            writer.write("Numero de zonas: " + zonas.getLenght());
            writer.newLine();
            writer.write("Numero de propiedades: " + propiedades.getLenght());
            writer.newLine();
            writer.write("Numero de propietarios: " + propietarios.getLenght());
            writer.newLine();
            writer.write("Numero de gastos: " + gastos.getLenght());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void propiedades(ListaComunidades comunidades, ListaPropietarios propietarios, ListaZonas zonas, ListaPropiedades propiedades, ListaGastos gastos){

        String nombreArchivo = "propiedades.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("PROPIEDADES: ");
            writer.newLine();
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%-6s %-6s %-25s %-15s %s", "Cod", "m2", "C. Nombre Propietario", "Cuotas", "Informacion adicional"));
            writer.newLine();
            writer.write("----------------------------------------------------------------------------------------------");
            writer.newLine();
            for (Propiedad propiedad : propiedades.getPropiedadesList()) {
                for (Propietario propietario : propietarios.getPropietariosList()) {
                    if (propietario.getCodigo().equals(propiedad.getCodPropietario())) {
                        String infoEspecifica = "";
                        if (propiedad.getTipoEspecifico().equals("VH")) {
                            infoEspecifica = "Vivienda habitada, " + propiedad.getDetalleEspecifico() + " habitaciones.";
                        } else if (propiedad.getTipoEspecifico().equals("VNH")) {
                            infoEspecifica = "Vivienda no habitada, " + propiedad.getDetalleEspecifico() + " habitaciones.";
                        } else if (propiedad.getTipoEspecifico().equals("A")) {
                            infoEspecifica = "Abierta, ";
                        } else if (propiedad.getTipoEspecifico().equals("C")) {
                            infoEspecifica = "Cerrada, ";
                        } else {
                            infoEspecifica = propiedad.getTipoEspecifico() + ", " + propiedad.getDetalleEspecifico() + ".";
                        }
                        if (propiedad.getDetalleEspecifico().equals("S")) {
                            infoEspecifica += "Con trasteo.";
                        } else if (propiedad.getDetalleEspecifico().equals("N")) {
                            infoEspecifica += "Sin trasteo.";
                        }
                        String[] porcentajes = new String[propiedad.getListaPorcentajes().size()];

                        for (int i = 0; i < propiedad.getListaPorcentajes().size(); i++) {
                            Porcentajes porcentaje = propiedad.getListaPorcentajes().get(i);
                            String unirpartes = Utils.inttostring(porcentaje.getPorciento()) + "%" + porcentaje.getCodigo();
                            porcentajes[i] = unirpartes;
                        }
                        String porcentajesStr = String.join(", ", porcentajes);
                        String linea = String.format("%-6s %-6d %-25s %-15s %s",
                                propiedad.getCodPropiedad(),
                                propiedad.getMetros2(),
                                propietario.getCodigo() + " " + propietario.getNombre(),
                                porcentajesStr,
                                infoEspecifica
                        );
                        writer.write(linea);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void propietarios(ListaPropietarios propietarios){

        String nombreArchivo = "propietarios.txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("PROPIETARIOS: ");
            writer.newLine();
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%-25s %-25s %s", "C. Nombre Propietario", "Email", "Propiedades"));
            writer.newLine();
            writer.write("-----------------------------------------------------------------------------");
            writer.newLine();

            for (Propietario propietario : propietarios.getPropietariosList()) {

                StringBuilder propiedadesStr = new StringBuilder();

                for (Propiedad propiedad : propietario.getPropiedades()) {
                    propiedadesStr.append(propiedad.getCodPropiedad()).append(", ");
                }
                if (propiedadesStr.length() > 0) {
                    propiedadesStr.delete(propiedadesStr.length() - 2, propiedadesStr.length());
                }

                String linea = String.format("%-25s %-25s %s",
                        propietario.getCodigo() + " " + propietario.getNombre(),
                        propietario.getEmail(),
                        propiedadesStr
                );
                writer.write(linea);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cuotas(ListaPropietarios propietarios, ListaPropiedades propiedades, ListaGastos gastos) {
        String nombreArchivo = "cuotas.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("PROPIEDADES: ");
            writer.newLine();
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%-6s %-25s %-23s %-24s", "", "", "Porcentajes", "Importes"));
            writer.newLine();
            writer.write(String.format("%-6s %-25s %-4s %-4s %-8s %-7s %-7s %-10s %s", "CPd.", "Nombre Propietario", "%E", "%G", "%C", "E.", "G.", "C.", "Total"));
            writer.newLine();
            writer.write("------------------------------------------------------------------------------------------------------------");
            writer.newLine();

            int propiedadesCount = 1;
            int porcentajE = 0;
            int porcentajG = 0;
            int porcentajC = 0;
            double importE = 0;
            double importG = 0;
            double importC = 0;
            double tot = 0;

            for (Propiedad prop : propiedades.getPropiedadesList()) {
                for (Propietario propietario : propietarios.getPropietariosList()) {
                    if (prop.getCodPropietario().equals(propietario.getCodigo())) {

                        String porcentajeE = "0";
                        String porcentajeG = "0";
                        String porcentajeC = "0";

                        for (Porcentajes porcentajes : prop.getListaPorcentajes()) {
                            if (porcentajes.getCodigo().equals("E")) {
                                porcentajeE = Utils.inttostring(porcentajes.getPorciento());
                                porcentajE += porcentajes.getPorciento();
                            } else if (porcentajes.getCodigo().equals("G")) {
                                porcentajeG = Utils.inttostring(porcentajes.getPorciento());
                                porcentajG += porcentajes.getPorciento();
                            } else if (porcentajes.getCodigo().equals("C")) {
                                porcentajeC = Utils.inttostring(porcentajes.getPorciento());
                                porcentajC += porcentajes.getPorciento();
                            }
                        }

                        String importeE = "0";
                        String importeG = "0";
                        String importeC = "0";

                        int sumaE = 0;
                        int sumaG = 0;
                        int sumaC = 0;

                        for (Gasto gasto : gastos.getGastosList()) {
                            for(Porcentajes porcentajes : prop.getListaPorcentajes()){
                                if(porcentajes.getCodigo().equals(gasto.getZona())){
                                    if(porcentajes.getCodigo().equals("E")){
                                        sumaE += gasto.getImporte();
                                    }
                                    if(porcentajes.getCodigo().equals("G")){
                                        sumaG += gasto.getImporte();

                                    }
                                    if(porcentajes.getCodigo().equals("C")) {
                                        sumaC += gasto.getImporte();

                                    }
                                }
                            }
                        }
                        double porE = Utils.stringtodouble(porcentajeE);
                        double valorE = sumaE * porE / 100;
                        double porG = Utils.stringtodouble(porcentajeG);
                        double valorG = sumaG * porG / 100;
                        double porC = Utils.stringtodouble(porcentajeC);
                        double valorC = sumaC * porC / 100;

                        double total = valorE + valorG + valorC;
                        DecimalFormat df = new DecimalFormat("#.##");
                        String totalFormatedo = df.format(total);

                        importeE = Double.toString(valorE);
                        importeG = Double.toString(valorG);
                        importeC = Double.toString(valorC);
                        String linea = String.format("%-6s %-25s %-4s %-4s %-8s %-7s %-7s %-10s %s",
                                prop.getCodPropiedad(),
                                propietario.getNombre(),
                                porcentajeE,
                                porcentajeG,
                                porcentajeC,
                                importeE,
                                importeG,
                                importeC,
                                totalFormatedo
                        );
                        writer.write(linea);
                        writer.newLine();
                        importE += valorE;
                        importC += valorC;
                        importG += valorG;
                        tot += total;
                    }

                }
                propiedadesCount++;
            }
            int imporE = (int) importE;
            int imporG = (int) importG;
            int imporC = (int) importC;
            int total = (int) tot;
            writer.write("------------------------------------------------------------------------------------------------------------");
            writer.newLine();
            String linea = String.format("%-31s %-4s %-4s %-8s %-7s %-7s %-10s %s",
                    "Propiedades: " + propiedadesCount,
                    porcentajE,
                    porcentajG,
                    porcentajC,
                    imporE,
                    imporG,
                    imporC,
                    total
            );
            writer.write(linea);


            writer.newLine();
            writer.newLine();
            writer.write("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
            writer.newLine();
            writer.newLine();
            writer.write("PORCENTAJES POR PROPIETARIOS: ");
            writer.newLine();
            writer.newLine();
            writer.newLine();
            writer.write(String.format("%-6s %-25s %-23s %-24s", "", "", "Porcentajes", "Importes"));
            writer.newLine();
            writer.write(String.format("%-6s %-25s %-4s %-4s %-8s %-7s %-7s %-10s %s", "Cd.", "Nombre Propietario", "%E", "%G", "%C", "E.", "G.", "C.", "Total"));
            writer.newLine();
            writer.write("------------------------------------------------------------------------------------------------------------");
            writer.newLine();

            int propietariosCount = 1;
            int porcentajE2 = 0;
            int porcentajG2 = 0;
            int porcentajC2 = 0;
            double importE2 = 0;
            double importG2 = 0;
            double importC2 = 0;
            double tot2 = 0;

            for(Propietario propietario : propietarios.getPropietariosList()) {
                double porcentajeE = 0;
                double porcentajeG = 0;
                double porcentajeC = 0;
                double importeE = 0;
                double importeG = 0;
                double importeC = 0;
                double total2 = 0;

                for (Propiedad propiedad : propiedades.getPropiedadesList()) {
                    if (propietario.getCodigo().equals(propiedad.getCodPropietario())) {
                        for (Porcentajes porcentajes : propiedad.getListaPorcentajes()) {
                            if(porcentajes.getCodigo().equals("E")){
                                porcentajeE += porcentajes.getPorciento();
                            } else if (porcentajes.getCodigo().equals("G")) {
                                porcentajeG += porcentajes.getPorciento();
                            } else if (porcentajes.getCodigo().equals("C")){
                                porcentajeC += porcentajes.getPorciento();
                            }
                        }

                        int sumaE = 0;
                        int sumaG = 0;
                        int sumaC = 0;

                        for (Gasto gasto : gastos.getGastosList()) {
                                    if (gasto.getZona().equals("E")) {
                                        sumaE += gasto.getImporte();
                                    }
                                    if (gasto.getZona().equals("G")) {
                                        sumaG += gasto.getImporte();

                                    }
                                    if (gasto.getZona().equals("C")) {
                                        sumaC += gasto.getImporte();
                                    }
                        }

                        importeE = sumaE * porcentajeE / 100;
                        importeG = sumaG * porcentajeG / 100;
                        importeC = sumaC * porcentajeC / 100;

                        total2 = importeE + importeG + importeC;

                    }
                }
                int porE = (int) porcentajeE;
                int porG = (int) porcentajeG;
                int porC = (int) porcentajeC;
                DecimalFormat df = new DecimalFormat("#.##");
                String totalFormatedo = df.format(total2);
                String linea2 = String.format("%-6s %-25s %-4s %-4s %-8s %-7s %-7s %-10s %s",
                        propietario.getCodigo(),
                        propietario.getNombre(),
                        porE,
                        porG,
                        porC,
                        importeE,
                        importeG,
                        importeC,
                        totalFormatedo
                );
                writer.write(linea2);
                writer.newLine();
                propietariosCount++;
                porcentajE2 += porcentajeE;
                porcentajG2 += porcentajeG;
                porcentajC2 += porcentajeC;
                importE2 += importeE;
                importG2 += importeG;
                importC2 += importeC;
                tot2 += total2;
            }
            writer.write("------------------------------------------------------------------------------------------------------------");
            writer.newLine();
            propietariosCount --;
            int tota2 = (int) tot2;
            int impE = (int) importE2;
            int impG = (int) importG2;
            int impC = (int) importC2;
            String linea2 = String.format("%-31s %-4s %-4s %-8s %-7s %-7s %-10s %s",
                    "Propietarios: " + propietariosCount ,
                    porcentajE2,
                    porcentajG2,
                    porcentajC2,
                    impE,
                    impG,
                    impC,
                    tota2
            );
            writer.write(linea2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}