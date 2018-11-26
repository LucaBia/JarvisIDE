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
    public Text escribir() {
        Text myText = new Text("\n"+"print("+texto+")");
        myText.setFont(new Font("Arial", 30));
        myText.setFill(Color.WHITE);
        return myText;
    }
}
