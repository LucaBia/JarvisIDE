package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Instruccion implements Accion {
    private String instruccion;

    public void setInstruccion(String ins) {
        this.instruccion = ins;
    }

    public String getInstruccion() {
        return this.instruccion;
    }

    @Override
    public Text escribir() {
        Text myText = new Text("\n"+instruccion);
        myText.setFont(new Font("Arial", 30));
        myText.setFill(Color.WHITE);
        return myText;
    }
}
