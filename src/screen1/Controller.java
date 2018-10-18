package screen1;

import classes.Algoritmo;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    @FXML
    private AnchorPane pantallaCodigo;

    // Logger
    private Logger logger = Logger.getLogger(getClass().getName());
    private String result;

    // Threads
    private Thread	speechThread;
    private Thread	resourcesThread;

    // LiveRecognizer
    private LiveSpeechRecognizer recognizer;

    public void grabarButton(ActionEvent e) throws Exception {

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



        //SPEECH RECOGNITION

        // Loading Message
        logger.log(Level.INFO, "Cargando...\n");

        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        // Grammar
        configuration.setGrammarPath("resource:/grammars");
        configuration.setGrammarName("grammar");
        configuration.setUseGrammar(true);

        try {
            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        // Se inicia con el proceso del reconocimiento
        recognizer.startRecognition(true);

        // Inicialización del Thread (proceso de ejecución) para verificar lo dicho
        startSpeechThread(gc);
        // Inicializacion del segundo Thread que verifica que un microfono este disponible
        startResourcesThread();
    }

    //Thread que verifica o inicia el reconocimiento vocal (Thread principal)
    private void startSpeechThread(GraphicsContext gc) {

        if (speechThread != null && speechThread.isAlive())
            return;

        speechThread = new Thread(() -> {
            logger.log(Level.INFO, "Te escucho...\n");
            try {
                while (true) {
                    // Metodo que unicamente funcionará si se reconoce una palabra a la hora de hablar
                    SpeechResult speechResult = recognizer.getResult();
                    if (speechResult != null) {
                        //Toma como referencia el metodo ya compilado de SpeechResult.java
                        result = speechResult.getHypothesis();
                        System.out.println("Acabas de decir: " + result + "\n");
                        makeDecision(result, gc);
                    } else
                        logger.log(Level.INFO, "No entendí lo que acabas de decir.\n");

                }
            } catch (Exception ex) {
                logger.log(Level.WARNING, null, ex);
            }

            logger.log(Level.INFO, "SpeechThread se cerro (exploto)");
        });

        // Se inicia el thread
        speechThread.start();

    }

    // Este thread verifica que el recurso principal (microfono) este conectado y disponible para el uso
    private void startResourcesThread() {
        if (resourcesThread != null && resourcesThread.isAlive())
            return;

        resourcesThread = new Thread(() -> {
            try {
                //Detecta que el microfono este disponible
                while (true) {
                    if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                        logger.log(Level.INFO, "El microfono esta conectado.\n");
                    } else {
                        logger.log(Level.INFO, "El microfono no esta disponible.\n");
                    }
                    Thread.sleep(350);
                }
            } catch (InterruptedException ex) {
                logger.log(Level.WARNING, null, ex);
                resourcesThread.interrupt();
            }
        });
        // Se incia el thread
        resourcesThread.start();
    }

    private void makeDecision(String speech, GraphicsContext gc) {
        // se divide la oración
        String[] array = speech.split(" ");

        // Se busca el signo de la operacion matematica
        if ("start".equals(array[0])) {
            inicioDiagrama(gc);
        } else if ("finish".equals(array[0]) && ("program".equals(array[1]))) {
            //Se acaba el programa
            System.exit(0);
        }

    }

    private void inicioDiagrama(GraphicsContext gc) {
        //Se inicia el diagrama de flujo

        /*
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
        */

        Algoritmo.addInicio(gc);
        //gc.drawImage(Algoritmo.createInicio(), 400, 100);
    }

}
