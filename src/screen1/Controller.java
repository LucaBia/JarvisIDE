package screen1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private AnchorPane pantallaCodigo;


    public void grabarButton(ActionEvent e) {
        System.out.println("Hola");
        Canvas canvas = new Canvas(200, 150);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        pantallaCodigo.getChildren().add(canvas);
        //Scene scene = new Scene(pantallaCodigo, 600, 400, Color.LIGHTGRAY);

        //primaryStage.setScene(scene);
        // Título que se aparecerá en la ventana
        //primaryStage.setTitle("Dibujando formas con JavaFX");
        // Orden para que se muestre la ventana
        //primaryStage.show();

        /* DIBUJO DE LAS FORMAS */

        /* Se utiliza el objeto gc (GraphicsContext) que se ha obtenido
            anteriormente a partir del canvas de se ha creado */

        // Dibujo de un rectángulo vacío (strokeRect) que va a ocupar todo
        //  el espacio del canvas
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Cambiar a partir de este momento el color de las líneas a azul
        gc.setStroke(Color.BLUE);
        // Cambiar a partir de este momento el grosor de las líneas a 5 puntos
        gc.setLineWidth(5);
        // Dibujar una línea desde la posición (40,10) a (10,40)
        gc.strokeLine(40, 10, 10, 40);
        // Cambiar a partir de este momento el color de relleno a verde
        gc.setFill(Color.GREEN);
        // Dibujar un círculo a partir de la posición (10,60) de ancho 30 y alto 30
        gc.fillOval(10, 60, 30, 30);
        // Dibujar un círculo sin relleno
        gc.strokeOval(60, 60, 30, 30);
        // Dibujar un rectángulo con bordes redondeados a partir de la posición
        //  (110,60) de ancho 30, alto 30, radio de bordes con ancho 10 y alto 10
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        // Dibujar un rectángulo vacio
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);

        System.out.println("Hola");
    }

}
