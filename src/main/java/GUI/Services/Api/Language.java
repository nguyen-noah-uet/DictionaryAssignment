package GUI.Services.Api;

import java.util.ArrayList;
import java.util.List;

public class Language {
    public static String Auto_Detect = "";
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
        languages.add(new Language("Tự động phát hiện ngôn ngữ", ""));
        return getLanguages(languages);
    }

    public static List<Language> getDesLanguage() {
        List<Language> languages = new ArrayList<>();
        return getLanguages(languages);
    }

    private static List<Language> getLanguages(List<Language> languages) {
        languages.add(new Language("Tiếng Trung (giản thể)", "zh-Hans"));
        languages.add(new Language("Tiếng Trung (phồn thể)", "zh-Hant"));
        languages.add(new Language("Tiếng Anh", "en"));
        languages.add(new Language("Tiếng Pháp", "fr"));
        languages.add(new Language("Tiếng Đức", "de"));
        languages.add(new Language("Tiếng Ý", "it"));
        languages.add(new Language("Tiếng Nhật", "ja"));
        languages.add(new Language("Tiếng Hàn", "ko"));
        languages.add(new Language("Tiếng Lào", "lo"));
        languages.add(new Language("Tiếng Bồ Đào Nha (Brazil)", "pt"));
        languages.add(new Language("Tiếng Bồ Đào Nha (Bồ Đào Nha)", "pt-pt"));
        languages.add(new Language("Tiếng Nga", "ru"));
        languages.add(new Language("Tiếng Thái", "th"));
        languages.add(new Language("Tiếng Việt", "vi"));
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
