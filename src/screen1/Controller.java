package screen1;

import classes.*;
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
import javafx.scene.text.TextFlow;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    @FXML
    private AnchorPane pantallaCodigo;
    @FXML
    private AnchorPane pantallaFunciones;

    // Logger
    private Logger logger = Logger.getLogger(getClass().getName());
    private String result;

    // Threads
    private Thread	speechThread;
    private Thread	resourcesThread;

    // LiveRecognizer
    private LiveSpeechRecognizer recognizer;

    // Canvas
    private Canvas canvas;
    private GraphicsContext gcPantallaCodigo;

    public void initialize() {
        //Se crea el canvas (area de dibujo)
        canvas = new Canvas(900, 570);
        gcPantallaCodigo = canvas.getGraphicsContext2D();
        //Se agrega el canvas a la seccion de la pantalla correspondiente
        pantallaCodigo.getChildren().add(canvas);
        //Cambiar el color de las líneas a azul
        gcPantallaCodigo.setStroke(Color.BLUE);
        //Cambiar el grosor de las líneas
        gcPantallaCodigo.setLineWidth(5);
        //Cambiar el color del texto
        gcPantallaCodigo.setFill(Color.WHITE);
        //Cambiar el tamaño y tipo de letra
        gcPantallaCodigo.setFont(new Font("BOARD_FONT", 30));

        //Algoritmo.addInicio(gcPantallaCodigo);
        //gcPantallaCodigo.drawImage(Algoritmo.createInicio(), 400, 100);
    }

    public void voiceButton(ActionEvent e) throws Exception {
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

    private void crearButton(ActionEvent e) {
        //todo
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
            inicioDiagrama();
        } else if ("create".equals(array[0])) {
            if ("function".equals(array[1])) {
                createFuncion();
            } else if ("if".equals(array[1])) {
                createIf();
            } else if ("input".equals(array[1])) {
                createInput();
            } else if ("instruction".equals(array[1])) {
                createInstruccion();
            } else if ("print".equals(array[1])) {
                createPrint();
            } else if ("var".equals(array[1])) {
                createVar();
            } else if ("while".equals(array[1])) {
                createWhile();
            }
        } else if ("add".equals(array[0])) {
            if ("if".equals(array[1])) {
                addIf();
            } else if ("input".equals(array[1])) {
                addInput();
            } else if ("instruction".equals(array[1])) {
                addInstruccion();
            } else if ("print".equals(array[1])) {
                addPrint();
            } else if ("var".equals(array[1])) {
                addVar();
            } else if ("while".equals(array[1])) {
                addWhile();
            } else if ("in".equals(array[1])) {
                if ("if".equals(array[2])) {
                    addInIf();
                } else if ("input".equals(array[2])) {
                    addInInput();
                } else if ("instruction".equals(array[2])) {
                    addInInstruccion();
                } else if ("print".equals(array[2])) {
                    addInPrint();
                } else if ("var".equals(array[2])) {
                    addInVar();
                } else if ("while".equals(array[2])) {
                    addInWhile();
                }
            } else if ("out".equals(array[1])) {
                if ("if".equals(array[2])) {
                    addOutIf();
                } else if ("input".equals(array[2])) {
                    addOutInput();
                } else if ("instruction".equals(array[2])) {
                    addOutInstruccion();
                } else if ("print".equals(array[2])) {
                    addOutPrint();
                } else if ("var".equals(array[2])) {
                    addOutVar();
                } else if ("while".equals(array[2])) {
                    addOutWhile();
                }
            }
        } else if ("finish".equals(array[0]) && ("program".equals(array[1]))) {
            //Se acaba el programa
            System.exit(0);
        }
    }

    private void inicioDiagrama() {
        //Se inicia el diagrama de flujo
        Algoritmo.addInicio(gcPantallaCodigo);
        //gcPantallaCodigo.drawImage(Algoritmo.createInicio(), 400, 100);
        //Algoritmo.createInicio2(gcPantallaCodigo);
        TextFlow espacioFunciones = new TextFlow();
        pantallaFunciones.getChildren().add(espacioFunciones);
    }

    private void createFuncion() {
        Funcion myFunction = new Funcion();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void createIf() {
        If myAcc = new If();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void createInput() {
        Input myAcc = new Input();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void createInstruccion() {
        Instruccion myAcc = new Instruccion();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void createPrint() {
        Print myAcc = new Print();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void createVar() {
        Variable myAcc = new Variable();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void createWhile() {
        While myAcc = new While();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addIf() {
        If myAcc = new If();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addInput() {
        Input myAcc = new Input();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addInstruccion() {
        Instruccion myAcc = new Instruccion();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addPrint() {
        Print myAcc = new Print();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addVar() {
        Variable myAcc = new Variable();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addWhile() {
        While myAcc = new While();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addInIf() {
        If myAcc = new If();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addInInput() {
        Input myAcc = new Input();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addInInstruccion() {
        Instruccion myAcc = new Instruccion();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addInPrint() {
        Print myAcc = new Print();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addInVar() {
        Variable myAcc = new Variable();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addInWhile() {
        While myAcc = new While();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addOutIf() {
        If myAcc = new If();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addOutInput() {
        Input myAcc = new Input();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addOutInstruccion() {
        Instruccion myAcc = new Instruccion();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addOutPrint() {
        Print myAcc = new Print();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addOutVar() {
        Variable myAcc = new Variable();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

    private void addOutWhile() {
        While myAcc = new While();
        //todo
        //se muestra en la pantalla creacion para editarla
    }

}
