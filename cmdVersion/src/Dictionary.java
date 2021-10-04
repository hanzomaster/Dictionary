import java.util.ArrayList;
import java.util.List;

public class Dictionary {
  private final List<Word> words;

  public Dictionary() {
    words = new ArrayList<>();
  }

  /** Adding new word. */
  public void addWord(Word word) {
    words.add(word);
  }

  /** Remove word from dictionary */
  public void removeWord(Word word) {
    words.remove(word);
  }

  /**
   * Find word in the dictionary.
   */
  public Word searchWord(String wordTarget) {
    for (Word word : words) {
      if (word.getWordTarget().equals(wordTarget)) {
        return word;
      }
    }

    return null;
  }

  /**
   * Return all words start with wordTarget
   */
  public List<Word> searchRelatedWords(String wordTarget) {
    List<Word> wordFounds = new ArrayList<>();

    for (Word word : words) {
      if (word.getWordTarget().startsWith(wordTarget)) {
        wordFounds.add(word);
      }
    }

    return wordFounds;
  }


  /** Lấy tất cả các từ có trong từ điển. */
  public List<Word> getAllWords() {
    return words;
  }
}
