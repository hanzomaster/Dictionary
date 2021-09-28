package backend.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
  private final String hostName = "localhost";
  private final String dbName = "edict";
  private final String userName = "root";
  private final String password = "Hide29f90892@"; // Your MySQL password go here

  private Connection connection = null;

  private PreparedStatement ps = null;

  /**
   * Connect to MySql database. Reference link: https://www.baeldung.com/java-connect-mysql
   *
   * @throws SQLException Can't access to database
   */
  public Database() throws SQLException {
    final String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName
        + "?autoReconnect=true&verifyServerCertificate=false&useSSL=true";

    connection = DriverManager.getConnection(connectionUrl, userName, password);
  }

  /**
   * Seach detail of word in database.
   * 
   * @param text word that need explaination
   * @return detail of {@code text}
   * @throws SQLException Can't access to database
   */
  public String searchWord(String text) throws SQLException {
    final String sqlSearchWordDetail = "select detail from dictionary where word=?";
    ps = connection.prepareStatement(sqlSearchWordDetail);

    ps.setString(1, text);

    ResultSet rs = ps.executeQuery();

    rs.next();
    String detail = rs.getString("detail");
    return detail;
  }

  /**
   * Adding a new word to the database.
   * 
   * @param word Word to be added
   * @param detail Definition of the word in <b>html</b> format
   * @throws SQLException Can't access to database
   */
  public void insertNewWord(final String word, final String detail) throws SQLException {
    final String sqlInsertData = "insert into dictionary (word, detail) values (?, ?)";
    ps = connection.prepareStatement(sqlInsertData);

    ps.setString(1, word);
    ps.setString(2, detail);

    ps.executeUpdate();
  }

  /**
   * Delete a word from database.
   * 
   * @param word Word to be deleted
   * @throws SQLException Can't access to database
   */
  public void deleteWord(final String word) throws SQLException {
    final String sqlDeleteData = "delete from dictionary where word=?";
    ps = connection.prepareStatement(sqlDeleteData);

    ps.setString(1, word);

    ps.executeUpdate();
  }

  /**
   * Update a word definition.
   * 
   * @param word Word that to be updated
   * @param detail new definition detail
   * @throws SQLException Can't access to database
   */
  public void updateWordDefinition(final String word, final String detail) throws SQLException {
    final String sqlUpdateData = "update dictionary set detail=? where word=?";
    ps = connection.prepareStatement(sqlUpdateData);

    ps.setString(1, detail);
    ps.setString(2, word);

    ps.executeUpdate();
  }

  /**
   * Export all database to CSV file. Reference link:
   * https://www.youtube.com/watch?v=b_KN2XAWtwQ&t=347s
   * 
   * @throws SQLException Can't access database
   * @throws FileNotFoundException Can't not find CSV file
   */
  public void exportDataToCsv() throws SQLException, FileNotFoundException {
    PrintWriter pw = new PrintWriter(new File("dictionary.csv"));
    StringBuilder sb = new StringBuilder();
    final String selectAllData = "select * from dictionary";
    ps = connection.prepareStatement(selectAllData);
    ResultSet rs = ps.executeQuery();

    // For your computer safety, please don't iterate through all the database. The
    // database have
    // 200000 rows and it might crash the app ¯\_(ツ)_/¯
    for (int index = 0; index < 100 && rs.next(); index++) {
      sb.append(rs.getString("id"));
      sb.append("\t");
      sb.append(rs.getString("word"));
      sb.append("\t");
      sb.append(rs.getString("detail"));
      sb.append("\r\n");
    }
    pw.write(sb.toString());
    pw.close();
  }

  /**
   * Select all word from database.
   * 
   * @throws SQLException Can't access to database
   */
  public ResultSet selectAllWord() throws SQLException {
    final String selectAllData = "select * from dictionary";
    ps = connection.prepareStatement(selectAllData);
    ResultSet rs = ps.executeQuery();
    return rs;
  }
}
