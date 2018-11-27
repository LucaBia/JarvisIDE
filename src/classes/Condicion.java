package classes;

public class Condicion {
    private String var1;
    private String var2;
    private String operadorLogico;

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public void setOperadorLogico(String operador) {
        this.operadorLogico = operador;
    }

    @Override
    public String toString() {
        return "("+var1+" "+operadorLogico+" "+var2+")";
    }
}
