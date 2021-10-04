import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {

  /**
   * Show all word in the dictionary
   */
  public static void showAllWords() {
    int i = 1;
    System.out.println("No\t|English\t|Vietnamese");
    for (Word word : DictionaryManagement.dictionary.getAllWords()) {
      System.out.println(i + "\t|" + word.getWordTarget() + "\t|" + word.getWordExplain());
      i++;
    }
  }

  /**
   * Add word from commandline and show all word added.
   */
  public static void dictonaryBasic() {
    DictionaryManagement.addWordFromCommandLine();
    showAllWords();

  }

  /**
   * Upper version.
   */
  public static void dictionaryAdvanced() {
    DictionaryManagement.insertFromFile();
    showAllWords();
    DictionaryManagement.dictionaryLookup();
  }

  /**
   * search related word.
   */
  public static List<Word> dictionarySeacher() {
    System.out.print("Insert word: ");
    Scanner newscanner = new Scanner(System.in);
    String wordTarget = newscanner.nextLine();
    newscanner.close();
    List<Word> newList = DictionaryManagement.dictionary.searchRelatedWords(wordTarget);
    return newList;
  }
}
