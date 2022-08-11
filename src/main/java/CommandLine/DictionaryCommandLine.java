package CommandLine;

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

  public void dictionaryAdvanced(Dictionary dictionary) {
    dictionaryManagement.insertFromFile(dictionary);
    showAllWords();
    dictionaryManagement.dictionaryLookup(dictionary);
  }

  public static void main(String[] args) throws Exception {
    DictionaryCommandLine dictionaryCommandLine = new DictionaryCommandLine();
    Dictionary dictionary = new Dictionary();
    dictionaryCommandLine.dictionaryAdvanced(dictionary);
  }
}
