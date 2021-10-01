import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DictionaryManagement {

  public void insertFromCommandLine() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Insert number of new words you would like to add: ");
    int numNewWords = scan.nextInt();
    scan.nextLine();

    for (int i = 1; i <= numNewWords; i++) {
      System.out.print(String.format("Insert word number %d: ", i));
      String wordTarget = scan.nextLine();
      while (wordTarget.equals("")) {
        System.out.print("Plese enter a word: ");
        wordTarget = scan.nextLine();
      }
      System.out.print("Insert part of speech in parentheses and meaning of word in Vietnamese: ");
      String wordExplain = scan.nextLine();
      while (wordExplain.equals("")) {
        System.out.print("Plese enter the difinition for the word above: ");
        wordExplain = scan.nextLine();
      }
      Dictionary.addWord(new Word(wordTarget, wordExplain));
    }
    scan.close();
  }

  /**
   * Insert data from file to trie.
   */
  public void insertFromFile() {
    String dataUrl = "../resources/data.txt";
    try (FileInputStream fileInputStream = new FileInputStream(dataUrl)) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
      while (bufferedReader.readLine() != null) {

      }
    } catch (IOException e) {
      System.err.println("Can't find the find in the given URL");
      e.printStackTrace();
    }
  }

  public void dictionaryLookup() {

  }

  public void insertNewWord(String word, String definitio) {

  }

  public void deleteWord(String word) {

  }

  public void modifyWordDefinition(String word) {

  }
}
