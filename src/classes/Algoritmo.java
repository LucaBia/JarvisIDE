package classes;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

public class Algoritmo {
    private List secuencia;

    public void addAccion(Accion ac) {
        this.secuencia.add(ac);
    }

    public void addIf(If ifNuevo) {
        this.secuencia.add(ifNuevo);
    }

    public void addInput(Input input) {
        this.secuencia.add(input);
    }

    public void addPrint(Print print) {
        this.secuencia.add(print);
    }

    public void addVariable(Variable var) {
        this.secuencia.add(var);
    }

    public void addWhile(While whileNuevo) {
        this.secuencia.add(whileNuevo);
    }

    public void guardarAlgoritmo() {
        //todo
    }

    public static WritableImage createInicio() {
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

        Line l = new Line();
        l.setStartX(50);
        l.setStartY(50);
        l.setEndX(50);
        l.setEndY(60);
        l.setStrokeWidth(5);
        l.setStroke(Color.BLUE);
        sPane.getChildren().add(l);

        return sPane.snapshot(parameters, null);
    }

    public static void addInicio(GraphicsContext gc) {
        //Dibujar un rectángulo vacio
        gc.strokeRoundRect(400, 5, 100, 40, 10, 10);
        gc.fillText("Inicio", 415, 40);
        //Dibujar una línea
        gc.strokeLine(450, 50, 450, 60);
    }
}
