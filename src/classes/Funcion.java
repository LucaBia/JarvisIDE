package classes;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
    public Label escribir() {
        //todo
        //Aqui defino como debe escribirse la funcion en el panel
        Label myLabel = new Label("Def "+nombre+"("+parametro1+","+parametro2+"):");
        return myLabel;
    }
}
