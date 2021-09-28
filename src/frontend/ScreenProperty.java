package frontend;

import backend.api.TranslateApi;
import backend.database.Database;
import backend.dictionary.TextToSpeech;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.URL;
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
  public String VNtrans = "";
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
      meaning = definition.searchWord(inputString);
      label.setText(meaning);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void speakout(ActionEvent event) {
    TextToSpeech speech = new TextToSpeech();
    speech.playSound(inputString);
  }

  public void googleapi(ActionEvent event) {
    TranslateApi newTrans = new TranslateApi();
    try {
      VNtrans = newTrans.translate(inputString);
      label.setText(VNtrans);
    } catch (UnirestException e) {
      // TODO
      System.out.println("Out of network");
    }

  }

  private void printStackTrace() {}

  public void initialize(URL location, ResourceBundle resources) {}

}
