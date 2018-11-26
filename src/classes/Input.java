package classes;

import javafx.scene.control.Label;

public class Input implements Accion {
    private String texto;
    private String valorIngreso;

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
