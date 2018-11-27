package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Print implements Accion {
    private String texto;

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
                myText.setText("\n        " + "print(" + texto + ")");
                break;
            case "Out":
                myText.setText("\n    " + "print(" + texto + ")");
                break;
            default:
                myText.setText("\n    " + "print(" + texto + ")");
                break;
        }
        return myText;
    }
}
