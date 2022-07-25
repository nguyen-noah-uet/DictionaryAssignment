package GUI.Services.Api;

import java.util.ArrayList;
import java.util.List;

public class Language {
    public static String Chinese_Simplified = "zh-Hans";
    public static String Chinese_Traditional = "zh-Hant";
    public static String English = "en";
    public static String French = "fr";
    public static String German = "de";
    public static String Italian = "it";
    public static String Japanese = "ja";
    public static String Korean = "ko";
    public static String Lao = "lo";
    public static String Portuguese_Brazil = "pt";
    public static String Portuguese_Portugal = "pt-pt";
    public static String Russian = "ru";
    public static String Thai = "th";
    public static String Vietnamese = "vi";
    private String name;
    private String acronym;
    public Language(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    public static List<Language> getSrcLanguage() {
        List<Language> languages = new ArrayList<>();
        languages.add(new Language("Auto detect", ""));
        return getLanguages(languages);
    }

    public static List<Language> getDesLanguage() {
        List<Language> languages = new ArrayList<>();
        return getLanguages(languages);
    }

    private static List<Language> getLanguages(List<Language> languages) {
        languages.add(new Language("Chinese Simplified", "zh-Hans"));
        languages.add(new Language("Chinese Traditional", "zh-Hant"));
        languages.add(new Language("English", "en"));
        languages.add(new Language("French", "fr"));
        languages.add(new Language("German", "de"));
        languages.add(new Language("Italian", "it"));
        languages.add(new Language("Japanese", "ja"));
        languages.add(new Language("Korean", "ko"));
        languages.add(new Language("Lao", "lo"));
        languages.add(new Language("Portuguese (Brazil)", "pt"));
        languages.add(new Language("Portuguese (Portugal)", "pt-pt"));
        languages.add(new Language("Russian", "ru"));
        languages.add(new Language("Thai", "th"));
        languages.add(new Language("Vietnamese", "vi"));
        return languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Override
    public String toString() {
        return name;
    }
}
