package frontend;

import backend.api.TranslateApi;
import backend.database.Database;
import backend.dictionary.TextToSpeech;
import backend.dictionary.WordSuggestion;
import com.mashape.unirest.http.exceptions.UnirestException;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ScreenProperty implements Initializable {
  public TextField inputText;
  public String inputString = "";
  public WebView webView;
  public HTMLEditor htmlEditor;

  private String meaning = "";
  private String vnTrans = "";
  static SuggestionProvider<String> provider;

  /**
   * Submit text to translate.
   * 
   * @param event take event
   */
  public void submit(ActionEvent event) {

    inputString = inputText.getText();

    try {
      final Database definition = new Database();
      meaning = definition.searchWord(inputString);

      if (meaning == "" || inputString == "") {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Word not found. Please use Google Translate.");
        alert.show();
        setHtml("");
        htmlToWebview(htmlEditor);
      } else {
        setHtml(meaning);
        htmlToWebview(htmlEditor);

      }
    } catch (SQLException e) {
      e.getMessage();
    }

  }

  private void setHtml(String s) {
    htmlEditor.setHtmlText(s);
  }

  private void htmlToWebview(HTMLEditor editor) {
    WebEngine webEngine = webView.getEngine();
    webEngine.loadContent(editor.getHtmlText(), "text/html");
  }

  /**
   * Initialize autocompletion when input text.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    provider = SuggestionProvider.create(WordSuggestion.suggestedWords);
    new AutoCompletionTextFieldBinding<>(inputText, provider);
  }

  public void speakout(ActionEvent event) {
    TextToSpeech speech = new TextToSpeech();
    speech.playSound(inputString);
  }

  /**
   * Translate text using Google API.
   */
  public void googleApi(ActionEvent event) {
    inputString = inputText.getText();

    try {
      vnTrans = TranslateApi.translate(inputString);
      setHtml(vnTrans);
      htmlToWebview(htmlEditor);
    } catch (UnirestException e) {
      System.out.println("Out of network");
    }

  }

  /**
   * Clear user input and definition.
   */
  public void xButtonClicked(ActionEvent event) {
    inputText.setText("");
    setHtml("");
    htmlToWebview(htmlEditor);
  }

  /**
   * Button to add/delete/modify database.
   */
  public void editButton(ActionEvent event) {

    try {

      Parent root1Parent =
          FXMLLoader.load(getClass().getResource("../resources/fxml/EditDatabase.fxml"));
      Stage newStage = new Stage();
      Scene scene1 = new Scene(root1Parent);
      scene1.getStylesheets()
          .add(getClass().getResource("../resources/fxml/EditDatabase.css").toExternalForm());
      newStage.setTitle("Edit Word Definition.");
      newStage.setResizable(false);
      newStage.setScene(scene1);
      newStage.show();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Export all database to CSV file.
   */
  public void exportButton(ActionEvent event) throws FileNotFoundException {

    Alert alert6 = new Alert(AlertType.CONFIRMATION);

    alert6.setHeaderText("Do you really want to export this dictionary?");

    Optional<ButtonType> result = alert6.showAndWait();

    if (result.get() == ButtonType.OK && result.isPresent()) {

      try {
        final Database exDatabase = new Database();
        exDatabase.exportDataToCsv();
        Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
        alert5.setContentText("Export complete.");
        alert5.show();

      } catch (SQLException e) {
        e.getMessage();
      }
    }
  }

}
