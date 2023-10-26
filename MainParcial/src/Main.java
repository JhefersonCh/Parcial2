public class Main {
    public static void main(String[] args) {

         ListaComunidades listaComunidades = new ListaComunidades();
         listaComunidades.leerDatos();
         //listaComunidades.showList();

         ListaZonas listaZonas = new ListaZonas();
         listaZonas.leerDatos();
         //listaZonas.showList();

         ListaPropiedades listaPropiedades = new ListaPropiedades();
         listaPropiedades.leerDatos();
         //listaPropiedades.showList();

        ListaPropietarios listaPropietarios = new ListaPropietarios();
        listaPropietarios.leerDatos(listaPropiedades);
        //listaPropietarios.showList();

        ListaGastos listaGastos = new ListaGastos();
        listaGastos.leerDatos();
        //listaGastos.showList();

        SalidasTXT salida = new SalidasTXT();
        salida.resumen(listaComunidades, listaPropietarios, listaZonas, listaPropiedades, listaGastos);
        salida.propiedades(listaComunidades, listaPropietarios, listaZonas, listaPropiedades, listaGastos);
        salida.propietarios(listaPropietarios);
        salida.cuotas(listaPropietarios, listaPropiedades, listaGastos);
    }
}