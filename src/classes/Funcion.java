package classes;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Funcion implements Accion {
    private String nombre;
    private List<String> parametros;
    private List<Accion> secuencia;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addParametro(String par) {
        Platform.runLater(() -> {
            this.parametros.add(par);
        });
    }

    public void addAccion(Accion ac) {
        this.secuencia.add(ac);
    }

    public void removeAccion(Accion ac) {
        this.secuencia.remove(ac);
    }

    @Override
    public void escribir() {
        //todo
        //Aqui defino como debe escribirse la funcion en el panel
        //Label
        //ap.getChildren().add()
    }
}
