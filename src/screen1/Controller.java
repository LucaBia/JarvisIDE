package screen1;

import classes.*;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML
    private AnchorPane pantallaCreacion;

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
            inicioPrograma();
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
            } else if ("variable".equals(array[1])) {
                createVar();
            } else if ("while".equals(array[1])) {
                createWhile();
            } else {
                System.out.println("Orden no reconocida");
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
            } else if ("variable".equals(array[1])) {
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
                } else if ("variable".equals(array[2])) {
                    addInVar();
                } else if ("while".equals(array[2])) {
                    addInWhile();
                } else {
                    System.out.println("Orden no reconocida");
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
                } else if ("variable".equals(array[2])) {
                    addOutVar();
                } else if ("while".equals(array[2])) {
                    addOutWhile();
                } else {
                    System.out.println("Orden no reconocida");
                }
            } else {
                System.out.println("Orden no reconocida");
            }
        } else if ("finish".equals(array[0]) && ("program".equals(array[1]))) {
            //Se acaba el programa
            System.exit(0);
        } else {
            System.out.println("Orden no reconocida");
        }
    }

    private void inicioPrograma() {
        //Se inicia el programa
        Platform.runLater(() -> {
            gcPantallaCodigo.drawImage(Algoritmo.createDibujoInicio(), 400, 5);
            TextFlow espacioFunciones = new TextFlow();
            pantallaFunciones.getChildren().add(espacioFunciones);
        });
    }

    private void createFuncion() {
        Funcion myFunction = new Funcion();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            //Se limpia el panel por si anteriormente habia algo
            pantallaCreacion.getChildren().clear();
            Label defLabel = new Label("def");
            defLabel.setLayoutX(15);
            defLabel.setLayoutY(60);
            //Text field para ingresar el nombre de la función
            TextField nombreTextField = new TextField();
            nombreTextField.setLayoutX(40);
            nombreTextField.setLayoutY(60);
            nombreTextField.setPrefWidth(75);
            //Label parentesis de apertura
            Label parentesis1Label = new Label("(");
            parentesis1Label.setLayoutX(125);
            parentesis1Label.setLayoutY(60);
            //Text field para ingresar el nombre del parametro
            TextField par1TextField = new TextField();
            par1TextField.setLayoutX(130);
            par1TextField.setLayoutY(60);
            par1TextField.setPrefWidth(50);
            //Label coma
            Label comaLabel = new Label(",");
            comaLabel.setLayoutX(185);
            comaLabel.setLayoutY(60);
            //Text field para ingresar el nombre del parametro
            TextField par2TextField = new TextField();
            par2TextField.setLayoutX(190);
            par2TextField.setLayoutY(60);
            par2TextField.setPrefWidth(50);
            //Label parentesis cierre
            Label parentesis2Label = new Label("):");
            parentesis2Label.setLayoutX(245);
            parentesis2Label.setLayoutY(60);
            //Button crear
            Button crearButton = new Button("Crear");
            crearButton.setLayoutX(150);
            crearButton.setLayoutY(100);
            crearButton.setOnAction(click -> crearFuncion(nombreTextField.getText(), par1TextField.getText(), par2TextField.getText()));
            pantallaCreacion.getChildren().addAll(defLabel, nombreTextField, parentesis1Label, par1TextField, comaLabel, par2TextField, parentesis2Label, crearButton);
        });
    }

    private void crearFuncion(String nombreFuncion, String par1, String par2) {
        //Se guarda la informacion proporcionada
        System.out.println("Hola");
        Funcion miFuncion = new Funcion();
        //Le doy la informacion a la funcion
        miFuncion.setNombre(nombreFuncion);
        miFuncion.addParametro(par1);
        miFuncion.addParametro(par2);
        //Mando a escribir la funcion en el panel correspondiente
        //miFuncion.escribir(pantallaFunciones);
    }

    private void createIf() {
        If myAcc = new If();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //Label If (
            Label ifLabel = new Label("if (");
            ifLabel.setLayoutX(15);
            ifLabel.setLayoutY(60);
            //TextField var1
            TextField var1TextField = new TextField();
            var1TextField.setLayoutX(35);
            var1TextField.setLayoutY(60);
            var1TextField.setPrefWidth(50);
            //TextFlied Operador logico
            TextField operadorTextField = new TextField();
            operadorTextField.setLayoutX(95);
            operadorTextField.setLayoutY(60);
            operadorTextField.setPrefWidth(25);
            //TextField var 2
            TextField var2TextField = new TextField();
            var2TextField.setLayoutX(130);
            var2TextField.setLayoutY(60);
            var2TextField.setPrefWidth(50);
            //Label Parentesis
            Label parentesisLabel = new Label("):");
            parentesisLabel.setLayoutX(185);
            parentesisLabel.setLayoutY(60);

            pantallaCreacion.getChildren().addAll(ifLabel, var1TextField, operadorTextField, var2TextField, parentesisLabel);
        });
    }

    private void createInput() {
        Input myAcc = new Input();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //TextField var
            TextField varTextField = new TextField();
            varTextField.setLayoutX(15);
            varTextField.setLayoutY(60);
            varTextField.setPrefWidth(50);
            //Label = input(
            Label inputLabel = new Label("= input(");
            inputLabel.setLayoutX(75);
            inputLabel.setLayoutY(60);
            //TextField texto a mostrar
            TextField textoTextField = new TextField();
            textoTextField.setLayoutX(125);
            textoTextField.setLayoutY(60);
            textoTextField.setPrefWidth(150);
            //Label )
            Label parentesisLabel = new Label(")");
            parentesisLabel.setLayoutX(285);
            parentesisLabel.setLayoutY(60);

            pantallaCreacion.getChildren().addAll(varTextField, inputLabel, textoTextField, parentesisLabel);
        });
    }

    private void createInstruccion() {
        Instruccion myAcc = new Instruccion();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //TextField instruccion
            TextField insTextField = new TextField();
            insTextField.setLayoutX(15);
            insTextField.setLayoutY(60);
            insTextField.setPrefWidth(300);

            pantallaCreacion.getChildren().addAll(insTextField);
        });
    }

    private void createPrint() {
        Print myAcc = new Print();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //Label print(
            Label printLabel = new Label("print(");
            printLabel.setLayoutX(15);
            printLabel.setLayoutY(60);
            //TextField texto a mostrar
            TextField textoTextField = new TextField();
            textoTextField.setLayoutX(50);
            textoTextField.setLayoutY(60);
            textoTextField.setPrefWidth(150);
            //Label )
            Label parentesisLabel = new Label(")");
            parentesisLabel.setLayoutX(210);
            parentesisLabel.setLayoutY(60);

            pantallaCreacion.getChildren().addAll(printLabel, textoTextField, parentesisLabel);
        });
    }

    private void createVar() {
        Variable myAcc = new Variable();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //TextField var name
            TextField varTextField = new TextField();
            varTextField.setLayoutX(15);
            varTextField.setLayoutY(60);
            varTextField.setPrefWidth(50);
            //Label =
            Label igualLabel = new Label("=");
            igualLabel.setLayoutX(75);
            igualLabel.setLayoutY(60);
            //TextField asignacion
            TextField asignacionTextField = new TextField();
            asignacionTextField.setLayoutX(90);
            asignacionTextField.setLayoutY(60);
            asignacionTextField.setPrefWidth(200);

            pantallaCreacion.getChildren().addAll(varTextField, igualLabel, asignacionTextField);
        });
    }

    private void createWhile() {
        While myAcc = new While();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //Label While (
            Label whileLabel = new Label("while (");
            whileLabel.setLayoutX(15);
            whileLabel.setLayoutY(60);
            //TextField var1
            TextField var1TextField = new TextField();
            var1TextField.setLayoutX(50);
            var1TextField.setLayoutY(60);
            var1TextField.setPrefWidth(50);
            //TextFlied Operador logico
            TextField operadorTextField = new TextField();
            operadorTextField.setLayoutX(110);
            operadorTextField.setLayoutY(60);
            operadorTextField.setPrefWidth(25);
            //TextField var 2
            TextField var2TextField = new TextField();
            var2TextField.setLayoutX(145);
            var2TextField.setLayoutY(60);
            var2TextField.setPrefWidth(50);
            //Label Parentesis
            Label parentesisLabel = new Label("):");
            parentesisLabel.setLayoutX(200);
            parentesisLabel.setLayoutY(60);

            pantallaCreacion.getChildren().addAll(whileLabel, var1TextField, operadorTextField, var2TextField, parentesisLabel);
        });
    }

    private void addIf() {
        If myAcc = new If();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addInput() {
        Input myAcc = new Input();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addInstruccion() {
        Instruccion myAcc = new Instruccion();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addPrint() {
        Print myAcc = new Print();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addVar() {
        Variable myAcc = new Variable();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addWhile() {
        While myAcc = new While();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addInIf() {
        If myAcc = new If();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addInInput() {
        Input myAcc = new Input();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addInInstruccion() {
        Instruccion myAcc = new Instruccion();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addInPrint() {
        Print myAcc = new Print();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addInVar() {
        Variable myAcc = new Variable();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addInWhile() {
        While myAcc = new While();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addOutIf() {
        If myAcc = new If();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addOutInput() {
        Input myAcc = new Input();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addOutInstruccion() {
        Instruccion myAcc = new Instruccion();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addOutPrint() {
        Print myAcc = new Print();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addOutVar() {
        Variable myAcc = new Variable();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

    private void addOutWhile() {
        While myAcc = new While();
        //Se muestra en la pantalla creacion para editarla
        Platform.runLater(() -> {
            pantallaCreacion.getChildren().clear();
            //todo
            pantallaCreacion.getChildren().addAll();
        });
    }

}
