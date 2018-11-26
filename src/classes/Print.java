package classes;

import javafx.scene.control.Label;

public class Print implements Accion {
    private String texto;

    public void setTexto(String txt) {
        this.texto = txt;
    }

    @Override
    public Label escribir() {
        //todo
        Label myLabel = new Label();
        return myLabel;
    }
}
