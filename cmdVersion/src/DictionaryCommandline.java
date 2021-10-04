import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
  /**
   * Show all word in the dictionary
   */
  public void showAllWords() {
    int i = 1;
    System.out.println("No\t|English\t|Vietnamese");
    for (Word word : DictionaryManagement.dictionary.getAllWords()) {
      System.out.println(i + "\t" + word.getWordTarget() + word.getWordExplain());
    }
  }

  /**
   * Add word from commandline and show all word added.
   */
  public void dictonaryBasic() {
    DictionaryManagement.addWordFromCommandLine();
    showAllWords();

  }

  public void dictionaryAdvanced() {
    DictionaryManagement.insertFromFile();
    showAllWords();
    DictionaryManagement.dictionaryLookup();
  }

  public List<Word> dictionarySeacher() {
    System.out.println("Insert word: ");
    Scanner scanner = new Scanner(System.in);
    String wordTarget = scanner.nextLine();
    return (List<Word>) DictionaryManagement.dictionary.searchRelatedWords(wordTarget);
  }

}
