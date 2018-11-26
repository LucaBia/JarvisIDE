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
    public Text escribir() {
        //todo
        Text myText = new Text("\n");
        myText.setFont(new Font("Arial", 30));
        myText.setFill(Color.WHITE);
        return myText;
    }
}
