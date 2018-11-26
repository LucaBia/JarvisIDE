package classes;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

public class Algoritmo {
    private List<Accion> secuencia;

    public void addAccion(Accion ac) {
        this.secuencia.add(ac);
    }

    public void removeAccion(Accion ac) {
        this.secuencia.remove(ac);
    }

    public void guardarAlgoritmo() {
        //todo
    }

    public static WritableImage createDibujoInicio() {
        //Se crea el espacio
        StackPane sPane = new StackPane();
        sPane.setPrefSize(100, 60);

        //Se crea la figura
        Rectangle r = new Rectangle(100, 40);
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setStroke(Color.BLUE);
        r.setStrokeWidth(5);
        sPane.getChildren().add(r);

        //Se crea el texto
        Text txt = new Text("Inicio");
        txt.setFill(Color.WHITE);
        sPane.getChildren().add(txt);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        return sPane.snapshot(parameters, null);
    }
}
