package GUI.Services.Api;

import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
public class ApiCognitiveMicrosoftSpeechToTextService {
    private static String subscriptionKey = "7f84558c619441e39269a128c1174db9";
    private static String subscriptionRegion = "southeastasia";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ApiCognitiveMicrosoftSpeechToTextService s = new ApiCognitiveMicrosoftSpeechToTextService();
        s.recognizeFromMicrophone();
    }

    public String recognizeFromMicrophone() throws InterruptedException, ExecutionException {
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(subscriptionKey, subscriptionRegion);
        speechConfig.setSpeechRecognitionLanguage("en-US");
        AudioConfig audioConfig = AudioConfig.fromDefaultMicrophoneInput();
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig, audioConfig);
        System.out.println("Speak into your microphone.");
        Future<SpeechRecognitionResult> task = speechRecognizer.recognizeOnceAsync();
        SpeechRecognitionResult speechRecognitionResult = task.get();

        if (speechRecognitionResult.getReason() == ResultReason.RecognizedSpeech) {
            System.out.println("RECOGNIZED: Text=" + speechRecognitionResult.getText());
        }
        else if (speechRecognitionResult.getReason() == ResultReason.NoMatch) {
            throw new ExecutionException("NOMATCH: Speech could not be recognized.", null);
        }
        else if (speechRecognitionResult.getReason() == ResultReason.Canceled) {
            CancellationDetails cancellation = CancellationDetails.fromResult(speechRecognitionResult);
            throw new ExecutionException("CANCELED: Reason=" + cancellation.getReason(), null);
        }
        return speechRecognitionResult.getText();

    }
}
