import java.util.StringTokenizer;

public class Utils {
    public static int stringtoint(String str){
        return Integer.parseInt(str);
    }

    public static double stringtodouble(String str){
        return Double.parseDouble(str);
    }

    public String doubleToString(double numero) {
        return Double.toString(numero);
    }

    boolean str_comprobarCadena( String linea, String cadena )
    {
        return linea.equals(cadena);
    }


    char stringtochar( String str )
    {
        if (str.length() > 0) {
            return str.charAt(0);
        }
        return ' '; // Devuelve un espacio si la cadena es nula
    }

    String chartostring( char c )
    {
        return String.valueOf(c);
    }

    public static String inttostring (int n) {
        return Integer.toString(n);
    }

    float redondearAlza( float num, int numdec)
    {
        float factor = (float) Math.pow(10.0, numdec);
        return (float) ( Math.ceil(num * factor) / (float) factor);
    }

    static void tokennizar_cadena(String c, StringBuffer ... a)  {
        StringTokenizer tokens;
        tokens= new StringTokenizer(c,";"); //StringTokenizer tokens = new StringTokenizer(linea);

        try{
            for (StringBuffer x : a){

                x.append(tokens.nextToken());

            }
        }
        catch(Exception e){

        }

    }
}