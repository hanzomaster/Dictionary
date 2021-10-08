package backend.dictionary;

import backend.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class WordSuggestion implements Runnable {
  private static Set<String> suggestedWords = new HashSet<>();

  public static Set<String> getSuggestedWords() {
    return suggestedWords;
  }

  /**
   * Parse all data to a HashSet for word suggestion.
   * 
   * @throws SQLException Can't access database
   */
  public static void parseDataToArrayList() throws SQLException {
    Database database = new Database();

    ResultSet rs = database.selectAllWord();

    while (rs.next()) {
      suggestedWords.add(rs.getString("word"));
    }
  }

  @Override
  public void run() {
    try {
      parseDataToArrayList();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
