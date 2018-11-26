package classes;

import javafx.scene.control.Label;

public class Instruccion implements Accion {
    private String instruccion;

    public void setInstruccion(String ins) {
        this.instruccion = ins;
    }

    public String getInstruccion() {
        return this.instruccion;
    }

    @Override
    public Label escribir() {
        //todo
        Label myLabel = new Label();
        return myLabel;
    }
}
