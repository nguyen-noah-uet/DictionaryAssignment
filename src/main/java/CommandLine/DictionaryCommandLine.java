package CommandLine;

public class DictionaryCommandLine {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    Dictionary dictionary = new Dictionary();
    public void showAllWords(){
        System.out.println("No\t|English\t\t|VietNamese");
        for (int i = 0; i < Dictionary.words.size(); i++) {
            System.out.printf("%-10s%-30s%-30s\n", i + 1, Dictionary.words.get(i).getWord_target(), Dictionary.words.get(i).getWord_explain());
        }
    }
    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }


//
//    public static void main (String[] args){
//
//        DictionaryCommandLine dictionaryCommandLine = new DictionaryCommandLine();
//
//        dictionaryCommandLine.dictionaryBasic();
//
//    }
}

