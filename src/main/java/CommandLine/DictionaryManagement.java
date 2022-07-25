package CommandLine;

import java.util.Scanner;

public class DictionaryManagement {
    public static Scanner sc = new Scanner(System.in);

    public static void insertFromCommandline() {
        int numOfWork = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numOfWork; i++) {
            String word_target = sc.nextLine();
            String word_explain = sc.nextLine();
            Word word = new Word(word_target, word_explain);
            Dictionary dictionary = new Dictionary();
            Dictionary.words.add(word);
        }
    }
}
