package pruebas;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

public class PruebaSpeechRecognizer {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        // Start recognition process pruning previously cached data.
        recognizer.startRecognition(true);
        System.out.println("Te escucho...");
        SpeechResult result = recognizer.getResult();

        // Print utterance string without filler words.
        System.out.println(result.getHypothesis());

        // Get individual words and their times.
        for (WordResult r : result.getWords()) {
            System.out.println(r);
        }

        // Pause recognition process. It can be resumed then with startRecognition(false).
        recognizer.stopRecognition();
    }
}
