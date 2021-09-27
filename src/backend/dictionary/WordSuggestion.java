package backend.dictionary;

import backend.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.lavivienpost.com/autocomplete-with-trie-code/
 */
public class WordSuggestion {
  static Trie word = new Trie();

  static class TrieNode {
    char data;
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isWholeWord = false;

    TrieNode(char c) {
      this.data = c;
    }
  }

  static class Trie {
    TrieNode root = new TrieNode(' ');

    public void insert(String word) {
      TrieNode current = root;
      for (char ch : word.toCharArray()) {
        if (!current.children.containsKey(ch)) {
          current.children.put(ch, new TrieNode(ch));
        }
        current = current.children.get(ch);
      }
      current.isWholeWord = true;
    }

    public List<String> autocomplete(String prefix) {
      List<String> res = new LinkedList<String>();
      TrieNode current = root;
      for (char ch : prefix.toCharArray()) {
        if (current.children.containsKey(ch)) {
          current = current.children.get(ch);
        } else {
          return res;
        }
      }
      helper(current, res, prefix.substring(0, prefix.length() - 1));
      return res;
    }

    void helper(TrieNode current, List<String> res, String prefix) {
      if (current.isWholeWord) {
        res.add(prefix + current.data);
      }
      for (Character c : current.children.keySet()) {
        helper(current.children.get(c), res, prefix + current.data);
      }
    }
  }

  /**
   * Import all word from database to Trie data structure.
   * 
   * @throws SQLException Can't access to database
   */

  public static void parseDataToTrie() throws SQLException {
    Database database = new Database();

    ResultSet rs = database.selectAllWord();

    for (int i = 0; i < 100 && rs.next(); i++) {
      word.insert(rs.getString("word"));
    }
  }
}
