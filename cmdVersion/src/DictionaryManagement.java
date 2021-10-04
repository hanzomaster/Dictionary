import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DictionaryManagement {

  /**
   * set a new dictionary list
   */
  public static final Dictionary dictionary = new Dictionary();

  /**
   * insert from commandline function.
   */
  public static void addWordFromCommandLine() {
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
      dictionary.addWord(new Word(wordTarget, wordExplain));
    }
    scan.close();
  }

  /**
   * Look up input word in dictionary.
   */
  public static void dictionaryLookup() {
    System.out.print("Type your word: ");
    Scanner scanner = new Scanner(System.in);
    String wordTarget = scanner.nextLine();
    if (dictionary.searchWord(wordTarget) != null) {
      System.out.println(wordTarget + " \t" + dictionary.searchWord(wordTarget).getWordExplain());
    } else {
      System.out.println("Can't find " + wordTarget + " in dictionary");
    }
    scanner.close();
  }

  /**
   * Delete input word.
   */
  public static void deleteWord(String word) {
    System.out.println("Insert word wanted to delete: ");
    Scanner sc = new Scanner(System.in);
    String wordDeleteTarget = sc.nextLine();
    Word wordDelete = dictionary.searchWord(wordDeleteTarget);
    if (wordDelete == null) {
      System.out.println("Can't find " + wordDeleteTarget + "in dictionary.");
    } else {
      dictionary.removeWord(wordDelete);
    }

    sc.close();
  }

  public static void modifyWordDefinition(String word) {
    System.out.println("Type your word: ");
    Scanner sc = new Scanner(System.in);
    String wordUpdateTarget = sc.nextLine();
    Word wordUpdate = dictionary.searchWord(wordUpdateTarget);
    int index = dictionary.getAllWords().indexOf(wordUpdate);
    if (index != -1) {
      System.out.print("Defition: ");
      String wordUpdateExplain = sc.nextLine();
      dictionary.getAllWords().set(index, new Word(wordUpdateTarget, wordUpdateExplain));
      System.out.print("You change " + wordUpdateTarget + " to " + wordUpdateExplain + ".");
    } else {
      System.out.println(wordUpdateTarget + " is not found.");
    }

    sc.close();
  }

  /**
   * Insert data from file to list.
   */
  public static void insertFromFile() {
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
        // Separte word and explain.
        for (int i = 1; i < line.length(); i++) {
          if (line.charAt(i) == '\t') {
            String wordTarget = line.substring(0, i);
            String wordExplain = line.substring(i + 2, line.length());
            dictionary.addWord(new Word(wordTarget, wordExplain));
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
   * Export dictionary to file.
   */
  public static void exportToFile() {
    String url = ".\\cmdVersion\\resources\\exportfile.txt";
    /* Create new file. */
    File file = null;
    boolean isCreate = false;
    try {
      file = new File(url);
      isCreate = file.createNewFile();
      if (isCreate) {
        System.out.print("File was created successfully!");
      } else {
        System.out.print("Failed to create a file.");
      }
    } catch (Exception e) {
      System.out.print(e);
    }
    /* Write word to file. */
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    try {
      fileWriter = new FileWriter(url, false);
      bufferedWriter = new BufferedWriter(fileWriter);
      for (Word word : dictionary.getAllWords()) {
        bufferedWriter.write(word.getWordTarget() + "\t" + word.getWordExplain());
        bufferedWriter.newLine();
        bufferedWriter.flush();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fileWriter.close();
        bufferedWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
