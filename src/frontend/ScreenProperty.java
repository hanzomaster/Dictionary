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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ScreenProperty implements Initializable {
  // public TextField height ;
  public TextField inputText;
  // public Label label = new Label();
  public String inputString = new String();
  public String meaning = "";
  public String vnTrans = "";
  public WebView webView;
  // WebEngine engine = webView.getEngine();
  public HTMLEditor htmlEditor;

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
      // label.setText(meaning);
      setHtml(meaning);
      htmlToWebview(htmlEditor);
      // engine.loadContent(meaning, "text/html");
    } catch (SQLException e) {
      e.getMessage();
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setContentText("Word not found. Please use Google Translate.");
      alert.show();

    }


  }

  public void setHtml(String s) {
    htmlEditor.setHtmlText(s);
    // engine.loadContent(htmlEditor.getHtmlText(), "text/html");
  }

  public void htmlToWebview(HTMLEditor editor) {
    WebEngine webEngine = webView.getEngine();
    webEngine.loadContent(editor.getHtmlText(), "text/html");
  }

  public void initialize(URL location, ResourceBundle resources) {

  }

  public void speakout(ActionEvent event) {
    TextToSpeech speech = new TextToSpeech();
    speech.playSound(inputString);
  }

  public void googleapi(ActionEvent event) {
    inputString = inputText.getText();

    try {
      vnTrans = TranslateApi.translate(inputString);
      setHtml(vnTrans);
      htmlToWebview(htmlEditor);
      // label.setText(VNtrans);
    } catch (UnirestException e) {
      System.out.println("Out of network");
    }

  }

  private void printStackTrace() {}


}
