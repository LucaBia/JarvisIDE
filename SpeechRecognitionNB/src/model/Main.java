package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;

//Librerias de la api
//Se utilizó Sphinx4 ya que es la api escrita en java
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Main {
    
        // Numeros en ingles a palabras
	EnglishNumberToString	numberToString	= new EnglishNumberToString();
	EnglishStringToNumber	stringToNumber	= new EnglishStringToNumber();

	// Logger
	private Logger logger = Logger.getLogger(getClass().getName());
	private String result;

	// Threads
	Thread	speechThread;
	Thread	resourcesThread;

	// LiveRecognizer
	private LiveSpeechRecognizer recognizer;

	//Cosntructor
	public Main() {

		// Loading Message
		logger.log(Level.INFO, "Cargando...\n");

		// Configuration necesita de 4 atributos para el reconocimiento de voz de alto nivel
                // Configuration se usa para suministrar los atributos requeridos y opcionales al reconocedor
		Configuration configuration = new Configuration();
                // Estos son los 4 atributos:
		// Es el Set path para el modelo acustico
                    //configuration.setAcousticModelPath("resource:/hindi_acoustic/");
                configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
                // Set path para el diccionario
                    //configuration.setDictionaryPath("resource:/hindi_lm/hindi.dic");
                configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
                // Se configura el modelo del lenguaje
                    //configuration.setLanguageModelPath("resource:/hindi_lm/hindi.lm");
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
	protected void startSpeechThread() {
            
		if (speechThread != null && speechThread.isAlive())
			return;

		speechThread = new Thread(() -> {
			logger.log(Level.INFO, "Puedes comenzar a hablar...\n");
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
	protected void startResourcesThread() {
		if (resourcesThread != null && resourcesThread.isAlive())
			return;

		resourcesThread = new Thread(() -> {
			try {
                                //Detecta que el microfono este disponible
				while (true) {
					if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
						//logger.log(Level.INFO, "El microfono esta conectado.\n");
					} else {
						//logger.log(Level.INFO, "El microfono no esta disponible.\n");
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


	public void makeDecision(String speech) {
		// se divide la oración
		String[] array = speech.split(" ");

		// solo un numero
		if (array.length != 3)
			return;

		// Se buscan los dos numeros
		int number1 = stringToNumber.convert(array[0]);
		int number2 = stringToNumber.convert(array[2]);

		// Se calcula el resultado
		int calculationResult = 0;
		String symbol = "?";

		// Se busca el signo de la operacion matematica
		if ("plus".equals(array[1])) {
			calculationResult = number1 + number2;
			symbol = "+";
		} else if ("minus".equals(array[1])) {
			calculationResult = number1 - number2;
			symbol = "-";
		} else if ("multiply".equals(array[1])) {
			calculationResult = number1 * number2;
			symbol = "*";
		} else if ("division".equals(array[1])) {
			calculationResult = number1 / number2;
			symbol = "/";
		}

		String res = numberToString.convert(Math.abs(calculationResult));

		// Se imprime la operacion con palabras
		System.out.println("Dijiste:( " + speech + " )\n\t\t lo que da como resultado: "
				+ (calculationResult >= 0 ? "" : "minus ") + res + "  \n");

		// Se imprime la operacion con numeros
		System.out.println("Dijiste:( " + number1 + " " + symbol + " " + number2 + ")\n\t\t lo que da como resultado: "
				+ calculationResult + " ");

	}



	public static void main(String[] args) {

		new Main();


	}

}