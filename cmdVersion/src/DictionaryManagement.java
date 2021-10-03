import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.Scanner;
import org.graalvm.compiler.word.Word;

public class DictionaryManagement {


  /**
   * Insert data from file to trie.
   */
  public void insertFromFile() {
    // url file dictionaries.txt
    String url = ".\\cmdVersion\\resources\\data1.txt";

    // Read data from File with BufferedReader.
    FileInputStream fileInputStream = null;
    BufferedReader bufferedReader = null;
    try {
      fileInputStream = new FileInputStream(url);
      bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
      String line = bufferedReader.readLine();
      while (line != null) {
        // Xử lí xâu từ file text truyền vào mảng Word.
        for (int i = 1; i < line.length(); i++) {
          if (line.charAt(i) == '\t') {
            String wordTarget = line.substring(0, i);
            String wordExplain = line.substring(i + 1);
            Dictionary.addWord(new Word(wordTarget, wordExplain));
            break;
          }
        }
        line = bufferedReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // Đóng file.
      try {
        bufferedReader.close();
        fileInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * isert from commandline function.
   */
  public void addWordFromCommandLine() {
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
      System.out.print("Insert kind of word and meaning of word in Vietnamese: ");
      String wordExplain = scan.nextLine();
      while (wordExplain.equals("")) {
        System.out.print("Plese enter the definition for the word above: ");
        wordExplain = scan.nextLine();
      }
      Dictionary.addWord(new Word(wordTarget, wordExplain));
    }
    scan.close();
  }


  /**
   * Look up input word in dictionary.
   */
  public void dictionaryLookup() {
    System.out.println("Type your word: ");
    Scanner scanner = new Scanner(System.in);
    String word_target = scanner.nextLine();
    if (Dictionary.searchWord(word_target) != null) {
      System.out.println(word_target + " \t" + Dictionary.searchWord(word_target).getWordExplain());
    } else {
      System.out.println("Can't find " + word_target + " in dictionary");
    }
    scanner.close();
  }



  /**
   * Delete input word.
   * 
   * @param word
   */
  public void deleteWord(String word) {
    System.out.println("Insert word wanted to delete: ");
    Scanner sc = new Scanner(System.in);
    String wordDeleteTarget = sc.nextLine();
    Word wordDelete = Dictionary.searchWord(wordDeleteTarget);
    if (wordDelete == null) {
      System.out.println("Can't find " + wordDeleteTarget + "in dictionary.");
    } else {
      Dictionary.removeWord(wordDelete);
    }

    sc.close();
  }

  public void modifyWordDefinition(String word) {
    System.out.println("Type your word: ");
    Scanner sc = new Scanner(System.in);
    String wordUpdateTarget = sc.nextLine();
    Word wordUpdate = Dictionary.searchWord(wordUpdateTarget);
    int index = Dictionary.getAllWords().indexOf(wordUpdate);
    if (index != -1) {
      System.out.print("Defition: ");
      String wordUpdateExplain = sc.nextLine();
      Dictionary.getAllWords().set(index, new Word(wordUpdateTarget, wordUpdateExplain));
      System.out.print("You change " + wordUpdateTarget + " to " + wordUpdateExplain + ".");
    } else {
      System.out.println(wordUpdateTarget + " is not found.");
    }

    sc.close();
  }
}

