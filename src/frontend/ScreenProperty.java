package frontend;

import backend.database.Database;
import backend.dictionary.TextToSpeech;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ScreenProperty implements Initializable {
  // public TextField height ;
  public TextField inputText;
  public Label label = new Label();
  public String inputString = new String();
  public String meaning = "";
  // public HTMLEditor htmlEditor;

  /**
   * Submit text to translate.
   * 
   * @param event take event
   */
  public void submit(ActionEvent event) {

    // TranslateApi newTrans = new TranslateApi();
    inputString = inputText.getText();

    try {
      final Database definition = new Database();
      ResultSet rs = definition.searchWord(inputString);
      rs.next();
      meaning = rs.getString("detail");
      label.setText(meaning);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void speakout(ActionEvent event) {
    TextToSpeech speech = new TextToSpeech();
    speech.playSound(inputString);
  }

  public void initialize(URL location, ResourceBundle resources) {}

}
