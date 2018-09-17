package screen1;

import classes.Algoritmo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Controller {
    @FXML
    private AnchorPane pantallaCodigo;

    public void grabarButton(ActionEvent e) {
        //Se crea el canvas (area de dibujo)
        Canvas canvas = new Canvas(900, 570);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //Se agrega el canvas a la seccion de la pantalla correspondiente
        pantallaCodigo.getChildren().add(canvas);
        //Cambiar el color de las líneas a azul
        gc.setStroke(Color.BLUE);
        //Cambiar el grosor de las líneas
        gc.setLineWidth(5);
        //Cambiar el color del texto
        gc.setFill(Color.WHITE);
        //Cambiar el tamaño y tipo de letra
        gc.setFont(new Font("BOARD_FONT", 30));

        Algoritmo.addInicio(gc);
        gc.drawImage(Algoritmo.createInicio(), 400, 100);
    }

}
