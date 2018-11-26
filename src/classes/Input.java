package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Input implements Accion {
    private String variable;
    private String texto;

    public void setVariable(String var) {
        this.variable = var;
    }

    public void setTexto(String txt) {
        this.texto = txt;
    }

    @Override
    public Text escribir() {
        Text myText = new Text("\n"+variable+" = input("+texto+")");
        myText.setFont(new Font("Arial", 30));
        myText.setFill(Color.WHITE);
        return myText;
    }
}
