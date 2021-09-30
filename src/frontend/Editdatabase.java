package frontend;

import backend.database.Database;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;


public class Editdatabase {

  public TextField word;

  public HTMLEditor htmlEditor;

  // public TextArea taText;

  private String inputWord = "";

  private String inputDefinition = "";


  /**
   * Add new word and definition to database.
   */
  public void addButtonClicked(ActionEvent event) {

    inputWord = word.getText();

    inputDefinition = htmlEditor.getHtmlText();

    if (htmlEditor.getHtmlText()
        .equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
      inputDefinition = "";
    }

    if (inputWord == "" || inputDefinition == "") {
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

      alert1.setContentText("Error. Please check your input.");
      alert1.setTitle("ERROR");

      alert1.show();
    } else {
      try {
        final Database wordAdd = new Database();

        if (wordAdd.searchWord(inputWord) != "") {
          Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

          alert2.setContentText("Error. Your word has already been in this dictionary.");
          alert2.setTitle("ERROR");

          alert2.show();
        } else {

          wordAdd.insertNewWord(inputWord, inputDefinition);

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
  public void deleteButtonClicked(ActionEvent event) {
    inputWord = word.getText();

    try {
      final Database wordDelete = new Database();

      wordDelete.deleteWord(inputWord);

      Alert alert3 = new Alert(Alert.AlertType.INFORMATION);

      alert3.setContentText("Word delete successfully.");

      alert3.setTitle("SUCCESSFULLY");

      alert3.show();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Modify word definiton in database.
   */
  public void modifyButtonClicked(ActionEvent event) {
    inputWord = word.getText();
    inputDefinition = htmlEditor.getHtmlText();

    if (htmlEditor.getHtmlText()
        .equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
      inputDefinition = "";
    }

    try {
      final Database wordModify = new Database();

      if (inputWord == "" || inputDefinition == "") {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        alert1.setContentText("Error. Please check your input.");
        alert1.setTitle("ERROR");

        alert1.show();
      } else if (wordModify.searchWord(inputWord) == "") {
        Alert alert4 = new Alert(Alert.AlertType.INFORMATION);

        alert4.setContentText("Error. This word has not been existed.");

        alert4.setTitle("ERROR");

        alert4.show();
      } else {

        wordModify.updateWordDefinition(inputWord, inputDefinition);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);

        alert3.setContentText("Word definition change successfully.");

        alert3.setTitle("SUCCESSFULLY");

        alert3.show();
        System.out.println(inputDefinition.length());
        System.out.println(inputDefinition);
        System.out.println(
            "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
