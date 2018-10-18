package screen1;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    // Logger
    private Logger logger = Logger.getLogger(getClass().getName());
    private String result;

    // Threads
    Thread	speechThread;
    Thread	resourcesThread;

    // LiveRecognizer
    private LiveSpeechRecognizer recognizer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("screen1.fxml"));
        primaryStage.setTitle("Jarvis IDE");
        primaryStage.setScene(new Scene(root, 1300, 650));
        primaryStage.show();

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
        startSpeechThread();
        // Inicializacion del segundo Thread que verifica que un microfono este disponible
        startResourcesThread();

    }

    //Thread que verifica o inicia el reconocimiento vocal (Thread principal)
    private void startSpeechThread() {

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
                        makeDecision(result);
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


    private void makeDecision(String speech) {
        // se divide la oración
        String[] array = speech.split(" ");

        // Se busca el signo de la operacion matematica
        if ("start".equals(array[0])) {
            //Se inicia el diagrama de flujo
        } else if ("finish".equals(array[0])) {
            //Se acaba el programa
            System.exit(0);
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
