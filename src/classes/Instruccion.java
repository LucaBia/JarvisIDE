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
    public Text escribir(String indent) {
        Text myText = new Text();
        myText.setFont(new Font("Arial", 20));
        myText.setFill(Color.WHITE);
        switch (indent) {
            case "In":
                myText.setText("\n        " + instruccion);
                break;
            case "Out":
                myText.setText("\n    " + instruccion);
                break;
            default:
                myText.setText("\n    " + instruccion);
                break;
        }
        return myText;
    }
}
