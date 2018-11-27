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
    public Text escribir(String indent) {
        Text myText = new Text();
        myText.setFont(new Font("Arial", 20));
        myText.setFill(Color.WHITE);
        switch (indent) {
            case "In":
                myText.setText("\n        " + variable + " = input(" + texto + ")");
                break;
            case "Out":
                myText.setText("\n    " + variable + " = input(" + texto + ")");
                break;
            default:
                myText.setText("\n    " + variable + " = input(" + texto + ")");
                break;
        }
        return myText;
    }
}
