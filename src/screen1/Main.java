package screen1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("screen1.fxml"));
        primaryStage.setTitle("Jarvis IDE");
        primaryStage.setScene(new Scene(root, 1300, 650));
        primaryStage.show();
    }

    private void makeDecision(String speech) {
        // se divide la oración
        String[] array = speech.split(" ");

        // Se busca el signo de la operacion matematica
        if ("start".equals(array[0])) {
            //Se inicia el diagrama de flujo
        } else if ("finish".equals(array[0]) && ("program".equals(array[1]))) {
            //Se acaba el programa
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
