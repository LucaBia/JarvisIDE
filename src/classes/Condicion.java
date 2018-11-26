package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Condicion implements Accion {
    private String var1;
    private String var2;
    private String operadorLogico;

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public void setOperadorLogico(String operador) {
        this.operadorLogico = operador;
    }

    @Override
    public Text escribir() {
        //todo
        Text myText = new Text("("+var1+" "+operadorLogico+" "+var2+")");
        myText.setFont(new Font("Arial", 30));
        myText.setFill(Color.WHITE);
        return myText;
    }
}
