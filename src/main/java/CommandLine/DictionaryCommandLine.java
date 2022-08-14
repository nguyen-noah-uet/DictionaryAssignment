package CommandLine;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandLine {
  DictionaryManagement dictionaryManagement = new DictionaryManagement();

  public void showAllWords() {
    System.out.println("No\t\t  |English\t\t\t\t  |VietNamese");
    for (int i = 0; i < Dictionary.words.size(); i++) {
      System.out.printf(
          "%-10s%-25s%-25s\n",
          i + 1,
          Dictionary.words.get(i).getWord_target(),
          Dictionary.words.get(i).getWord_explain());
    }
  }

  public void dictionaryBasic(Dictionary dictionary) {
    dictionaryManagement.insertFromCommandline(dictionary);
    showAllWords();
  }

  public void dictionaryAdvanced(Dictionary dictionary) throws IOException {
    dictionaryManagement.insertFromFile(dictionary);
    showAllWords();
    dictionaryManagement.dictionaryLookup(dictionary);
  }

  public void dictionarySearcher(Dictionary dic) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    for (Word word : dic.words) {
      if (word.getWord_target().contains(s)) {
        System.out.println(word.getWord_target() + "\t" + word.getWord_explain());
      }
    }
  }

//  public static void main(String[] args) throws IOException {
//    Dictionary dic = new Dictionary();
//    DictionaryManagement man = new DictionaryManagement();
//    DictionaryCommandLine cml = new DictionaryCommandLine();
//    cml.dictionaryAdvanced(dic);
//    //man.addWord(dic);
//    man.removeWord(dic);
//  }
}
