import java.util.ArrayList;
import java.util.List;

public class Dictionary {
  private static final List<Word> words = new ArrayList<>();

  public static void addWord(Word word) {
    words.add(word);
  }

  public static void removeWord(Word word) {
    words.remove(word);
  }

  /**
   * Search for word in dictionary.
   * 
   * @param wordTarget Word to be searched
   * @return that word if exist and meaning
   */
  public static Word searchWord(String wordTarget) {
    for (Word word : words) {
      if (word.getWordTarget().equals(wordTarget)) {
        return word;
      }
    }
    return null;
  }

  public static List<Word> getAllWords() {
    return words;
  }
}
