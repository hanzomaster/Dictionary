package backend.dictionary;

import backend.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class WordSuggestion {
  public static Set<String> suggestedWords = new HashSet<>();

  /**
   * Parse all data to a HashSet for word suggestion.
   * 
   * @throws SQLException Can't access database
   */
  public static void parseDataToArrayList() throws SQLException {
    Database database = new Database();

    ResultSet rs = database.selectAllWord();

    for (int i = 0; i < 1000 && rs.next(); i++) {
      suggestedWords.add(rs.getString("word"));
    }
  }
}
