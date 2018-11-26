package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Condicion implements Accion {
    private Variable var1;
    private Variable var2;
    private String OperadorLogico;

    public void setVar1(Variable var1) {
        this.var1 = var1;
    }

    public void setVar2(Variable var2) {
        this.var2 = var2;
    }

    public void setOperadorLogico(String operador) {
        this.OperadorLogico = operador;
    }

    @Override
    public Text escribir() {
        //todo
        Text myText = new Text("\n");
        myText.setFont(new Font("Arial", 30));
        myText.setFill(Color.WHITE);
        return myText;
    }
}
