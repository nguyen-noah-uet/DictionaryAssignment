package CommandLine;

import java.util.ArrayList;

public class Dictionary {
    static ArrayList<Word> words = new ArrayList<Word>();
    
public int search(String word) {
    for (int i = 0; i < words.size(); i++) {
      if (word.equals(words.get(i).getWord_target())) return i;
    }
    return -1;
  }
}
