package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class While implements Accion {
    private Condicion condicion;
    private List<Accion> secuencia;

    public void setCondicion(Condicion cond) {
        this.condicion = cond;
    }

    public void addAccion(Accion ac) {
        this.secuencia.add(ac);
    }

    public void removeAccion(Accion ac) {
        this.secuencia.remove(ac);
    }

    @Override
    public Text escribir(String indent) {
        //todo
        Text myText = new Text();
        myText.setFont(new Font("Arial", 20));
        myText.setFill(Color.WHITE);
        myText.setText("\n    " + "while " + (condicion) + ":");
        return myText;
    }
}
