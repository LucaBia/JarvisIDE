package classes;

import javafx.scene.control.Label;

public class Variable implements Accion {
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

    @Override
    public Label escribir() {
        //todo
        Label myLabel = new Label();
        return myLabel;
    }
}
