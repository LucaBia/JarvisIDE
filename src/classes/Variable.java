package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Variable implements Accion {
    private String nombre;
    private String valor;

    public void setValor(String nombreVariable, String valor) {
        this.nombre = nombreVariable;
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }

    @Override
    public Text escribir(String indent) {
        Text myText = new Text();
        myText.setFont(new Font("Arial", 20));
        myText.setFill(Color.WHITE);
        switch (indent) {
            case "In":
                myText.setText("\n        " + nombre + " = " + valor);
                break;
            case "Out":
                myText.setText("\n    " + nombre + " = " + valor);
                break;
            default:
                myText.setText("\n    " + nombre + " = " + valor);
                break;
        }
        return myText;
    }
}
