package GUI.Services.Api;

import java.util.ArrayList;

public class TranslateResult {
    public DetectedLanguage detectedLanguage;
    public ArrayList<Translation> translations;

    public DetectedLanguage getDetectedLanguage() {
        return detectedLanguage;
    }


    public ArrayList<Translation> getTranslations() {
        return translations;
    }

}

