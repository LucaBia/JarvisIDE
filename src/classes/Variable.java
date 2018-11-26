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
    public Text escribir() {
        Text myText = new Text("\n"+nombre+" = "+valor);
        myText.setFont(new Font("Arial", 30));
        myText.setFill(Color.WHITE);
        return myText;
    }
}
