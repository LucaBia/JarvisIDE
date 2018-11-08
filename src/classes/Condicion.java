package classes;

public class Condicion implements Accion {
    private Variable var1;
    private Variable var2;
    private String OperadorLogico;

    public void setVar1(Variable var1) {
        this.var1 = var1;
    }

    public void setVar2(Variable var2) {
        this.var2 = var2;
    }

    public void setOperadorLogico(String operador) {
        this.OperadorLogico = operador;
    }

    @Override
    public void escribir() {
        //todo
    }
}
