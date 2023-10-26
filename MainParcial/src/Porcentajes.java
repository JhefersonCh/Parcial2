public class Porcentajes {
    private String codigo;
    private int porciento;

    public Porcentajes(String codigo, int porciento) {
        this.codigo = codigo;
        this.porciento = porciento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPorciento() {
        return porciento;
    }

    public void setPorciento(int porciento) {
        this.porciento = porciento;
    }
}
