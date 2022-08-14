package CommandLine;
import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
  public static Scanner sc = new Scanner(System.in);

  public void insertFromCommandline(Dictionary dictionary) {
    int numOfWork = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < numOfWork; i++) {
      String word_target = sc.nextLine();
      String word_explain = sc.nextLine();
      Word word = new Word(word_target, word_explain);
      dictionary.words.add(word);
    }
  }

  public void insertFromFile(Dictionary dictionary) throws IOException {
    FileReader file = new FileReader("dictionaries.txt");
    BufferedReader br = new BufferedReader(file);
    String str;
    while ((str = br.readLine()) != null) {
      String[] line = str.split("\t");
      Word word = new Word(line[0], line[1]);
      dictionary.words.add(word);
    }
  }

  public void dictionaryLookup(Dictionary dictionary) {
    Scanner scanner = new Scanner(System.in);
    String sc = scanner.nextLine();
    int ins = dictionary.search(sc);
    if (ins == -1) {
      System.out.println("Từ bạn đã nhập chưa có");
    } else {

      System.out.printf(
          "%-10s%-25s%-25s\n",
          ins + 1,
          dictionary.words.get(ins).getWord_target(),
          dictionary.words.get(ins).getWord_explain());
    }
  }

  public void addWord(Dictionary dictonary) throws IOException {
    Word newWord = new Word();
    System.out.println("Nhập từ cần thêm:");
    newWord.setWord_target(sc.nextLine());
    newWord.setWord_explain(sc.nextLine());
    dictonary.words.add(newWord);
    dictionaryExportToFile(dictonary);
  }

  public void removeWord(Dictionary dictionary) throws IOException {
    System.out.println("Nhập từ cần xóa: ");
    String w = sc.nextLine();
    if (dictionary.search(w) >= 0) {
      dictionary.words.remove(dictionary.search(w));
      System.out.println("Đã xóa từ!");
    } else {
      System.out.println("Từ bạn đã nhập chưa có");
    }
    dictionaryExportToFile(dictionary);
  }

  /** sửa từ trong từ điển */
  public void fixDictionary(Dictionary dictionary) {
    System.out.println("Nhập từ cần sửa:");
    String word_fix = sc.nextLine();
    int index = dictionary.search(word_fix);
    if (index >= 0) {
      System.out.println("(Nhập cả từ và nghĩa , cách nhau bằng dấu tab.)");
      String str = sc.nextLine();
      String[] line = str.split("\t");
      dictionary.words.get(index).setWord_target(line[0]);
      dictionary.words.get(index).setWord_explain(line[1]);
      System.out.println("Sửa thành công!");
    } else {
      System.out.println("Từ bạn đã nhập chưa có");
    }
  }

  /** Xuất từ điển ra file. */
  public void dictionaryExportToFile(Dictionary dictionary) throws IOException {
    BufferedWriter out = new BufferedWriter(new FileWriter("dictionaries.txt"));
    for (Word word : dictionary.words) {
      out.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
    }
    out.flush();
    out.close();
  }
}
