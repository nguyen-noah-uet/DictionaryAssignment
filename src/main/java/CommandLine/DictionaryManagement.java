import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
  public static Scanner sc = new Scanner(System.in);

  public static void insertFromCommandline(Dictionary dictionary) {
    int numOfWork = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < numOfWork; i++) {
      String word_target = sc.nextLine();
      String word_explain = sc.nextLine();
      Word word = new Word(word_target, word_explain);
      dictionary.words.add(word);
    }
  }

  public void insertFromFile(Dictionary dictionary) {
    try {
      FileReader file = new FileReader("Dic.txt");
      BufferedReader br = new BufferedReader(file);
      String str;
      while ((str = br.readLine()) != null) {
        String[] line = str.split("\t");
        Word word = new Word(line[0], line[1]);
        dictionary.words.add(word);
      }
    } catch (IOException io) {
      System.out.println("Lỗi: " + io);
    }
  }

  public static void dictionaryLookup(Dictionary dictionary) {
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
}
