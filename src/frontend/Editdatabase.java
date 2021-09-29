package frontend;

import backend.database.Database;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;


public class Editdatabase {

  public TextField word;

  public HTMLEditor hEditor;

  public TextArea taText;

  private String inputWord = "";

  private String inputDefinition = "";

  /**
   * Add new word and definition to database.
   */
  public void addButtonClick(ActionEvent event) {

    inputWord = word.getText();

    taText.setText(hEditor.getHtmlText());

    inputDefinition = taText.getText();

    if (inputWord == "" || inputDefinition == "") {
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

      alert1.setContentText("Error. Please check your input.");
      alert1.setTitle("ERROR");

      alert1.show();
    } else {
      try {
        final Database wordModify = new Database();

        if (wordModify.searchWord(inputWord) != "") {
          Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

          alert2.setContentText("Error. Your word has already been in this dictionary.");
          alert2.setTitle("ERROR");

          alert2.show();
        } else {

          wordModify.insertNewWord(inputWord, inputDefinition);

          Alert alert = new Alert(Alert.AlertType.INFORMATION);

          alert.setContentText("Word add successfully.");

          alert.setTitle("SUCCESSFULLY");

          alert.show();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }

  }

  /**
   * Delete input word from database.
   */
  public void deleteButtonClick(ActionEvent event) {
    inputWord = word.getText();

    try {
      final Database wordModify = new Database();

      wordModify.deleteWord(inputWord);

      Alert alert3 = new Alert(Alert.AlertType.INFORMATION);

      alert3.setContentText("Word delete successfully.");

      alert3.setTitle("SUCCESSFULLY");

      alert3.show();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
