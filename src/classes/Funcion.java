package classes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class Funcion implements Accion {
    private String nombre;
    private String parametro1;
    private String parametro2;
    private List<Accion> secuencia;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setParametros(String par1, String par2) {
        this.parametro1 = par1;
        this.parametro2 = par2;
    }

    public void addAccion(Accion ac) {
        this.secuencia.add(ac);
    }

    public void removeAccion(Accion ac) {
        this.secuencia.remove(ac);
    }


    @Override
    public Text escribir(String indent) {
        //Aqui se define como debe escribirse la funcion en el panel
        /*Text Def = new Text("Def");
        Def.setFont(new Font("Arial", 20));
        Def.setFill(Color.SKYBLUE);

        Text NombreFuncion = new Text(nombre);
        NombreFuncion.setFont(new Font("Arial", 20));
        NombreFuncion.setFill(Color.LIGHTGREEN);

        Text Parametros = new Text("("+parametro1+", " + parametro2+")");
        Parametros.setFont(new Font("Arial", 20));
        Parametros.setFill(Color.ORANGE);*/

        //Text myText = new Text(Def + NombreFuncion + Parametros);

        Text myText = new Text("Def "+nombre+"("+parametro1+","+parametro2+")");
        myText.setFont(new Font("Arial", 20));
        myText.setFill(Color.WHITE);
        return myText;
    }
}
