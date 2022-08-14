package GUI.Services.Api;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

public class ApiCognitiveMicrosoftTextToSpeechService {
    private static final String SubscriptionKey = "7f84558c619441e39269a128c1174db9";
    private static final String ServiceRegion = "southeastasia";
    private final Dictionary<String, String> dict = new Hashtable<>();

    public ApiCognitiveMicrosoftTextToSpeechService() {
        dict.put(Language.Chinese_Simplified, "zh-CN-YunfengNeural");
        dict.put(Language.Chinese_Traditional, "zh-HK-HiuGaaiNeural");
        dict.put(Language.English, "en-US-JennyNeural");
        dict.put(Language.French, "fr-BE-CharlineNeural");
        dict.put(Language.German, "de-DE-AmalaNeural");
        dict.put(Language.Italian, "it-IT-ElsaNeural");
        dict.put(Language.Japanese, "ja-JP-NanamiNeural");
        dict.put(Language.Korean, "ko-KR-SunHiNeural");
        dict.put(Language.Lao, "lo-LA-KeomanyNeural");
        dict.put(Language.Portuguese_Brazil, "pt-BR-FranciscaNeural");
        dict.put(Language.Portuguese_Portugal, "pt-PT-FernandaNeural");
        dict.put(Language.Russian, "ru-RU-DariyaNeural");
        dict.put(Language.Thai, "th-TH-AcharaNeural");
        dict.put(Language.Vietnamese, "vi-VN-NamMinhNeural");
    }

    public void textToSpeech(String text, String languageAcronym) throws ExecutionException, InterruptedException {
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(SubscriptionKey, ServiceRegion);
        String voiceName = dict.get(languageAcronym);
        speechConfig.setSpeechSynthesisVoiceName(voiceName);

        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig);

        speechSynthesizer.SpeakTextAsync(text).get();
    }

//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        SpeechConfig speechConfig = SpeechConfig.fromSubscription(SubscriptionKey, ServiceRegion);
//
//        speechConfig.setSpeechSynthesisVoiceName("en-US-JennyNeural");
//
//        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig);
//
//        // Get text from the console and synthesize to the default speaker.
//        System.out.println("Enter some text that you want to speak >");
//        String text = new Scanner(System.in).nextLine();
//        if (text.isEmpty())
//        {
//            return;
//        }
//
//        SpeechSynthesisResult speechRecognitionResult = speechSynthesizer.SpeakTextAsync(text).get();
//
//        if (speechRecognitionResult.getReason() == ResultReason.SynthesizingAudioCompleted) {
//            System.out.println("Speech synthesized to speaker for text [" + text + "]");
//        }
//        else if (speechRecognitionResult.getReason() == ResultReason.Canceled) {
//            SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(speechRecognitionResult);
//            System.out.println("CANCELED: Reason=" + cancellation.getReason());
//
//            if (cancellation.getReason() == CancellationReason.Error) {
//                System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
//                System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
//                System.out.println("CANCELED: Did you set the speech resource key and region values?");
//            }
//        }
//
//        System.exit(0);
//    }
}