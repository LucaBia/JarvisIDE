package classes;

public class Variable {
    private String nombre;
    private String valor;
    private String tipoDeDato;

    public void setValor(String nombreVariable, String valor, String tipoDeDato) {
        this.nombre = nombreVariable;
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }

    public String getValor() {
        return this.valor;
    }
}
