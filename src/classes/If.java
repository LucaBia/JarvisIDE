package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class If implements Accion {
    private Condicion condicion;
    private List<Accion> secuenciaTrue;
    private List<Accion> secuenciaFalse;

    public void setCondicion(Condicion cond) {
        this.condicion = cond;
    }

    //TRUE
    public void addAccionT(Accion ac) {
        this.secuenciaTrue.add(ac);
    }

    public void removeAccionT(Accion ac) {
        this.secuenciaTrue.remove(ac);
    }

    //FALSE
    public void addAccionF(Accion ac) {
        this.secuenciaFalse.add(ac);
    }

    public void removeAccionF(Accion ac) {
        this.secuenciaFalse.remove(ac);
    }

    @Override
    public Text escribir() {
        //todo
        Text myText = new Text("\n"+"if "+condicion+":");
        myText.setFont(new Font("Arial", 30));
        myText.setFill(Color.WHITE);
        return myText;
    }

}
