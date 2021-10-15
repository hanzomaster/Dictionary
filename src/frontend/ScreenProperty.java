package frontend;

import backend.database.Database;
import backend.database.Historybase;
import backend.dictionary.TextToSpeech;
import backend.dictionary.WordSuggestion;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ScreenProperty implements Initializable {
  public TextField inputText;
  public String inputString = "";
  public WebView webView;
  public HTMLEditor htmlEditor;

  private Set<String> newSuggestedWord = new HashSet<>();
  static SuggestionProvider<String> provider;

  /**
   * Initialize autocompletion when input text.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    provider = SuggestionProvider.create(WordSuggestion.getSuggestedWords());
    new AutoCompletionTextFieldBinding<>(inputText, provider);
  }

  /**
   * Update the word suggestion while user inputting text.
   */
  public void getTextinField(KeyEvent event) {
    String newText = inputText.getText();
    if (newText.equals("")) {
      setHtml("");
      htmlToWebview(htmlEditor);
    }
    for (String relateword : WordSuggestion.getSuggestedWords()) {
      if (relateword.startsWith(newText) && !newText.equals("")) {
        newSuggestedWord.add(relateword);
      }

    }
    if (event.getCode() == KeyCode.ENTER) {
      inputString = inputText.getText();

      try {
        final Database definition = new Database();
        String meaning = definition.searchWord(inputString);
        if (meaning.equals("") || inputString.equals("")) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setContentText("Word not found. Please use Google Translate.");
          alert.show();
          setHtml("");
          htmlToWebview(htmlEditor);
        } else {
          setHtml(meaning);
          htmlToWebview(htmlEditor);
          Historybase.insertWordtoHistory(inputString);
        }
      } catch (SQLException e) {
        e.getMessage();
      }
    }
    // https://stackoverflow.com/questions/45778462/update-autocomplete-javafx
    provider.clearSuggestions();
    provider.addPossibleSuggestions(newSuggestedWord);
    newSuggestedWord.clear();
  }

  /**
   * Submit text to translate.
   * 
   * @param event take event
   */
  public void submit(ActionEvent event) {
    inputString = inputText.getText();

    try {
      final Database definition = new Database();
      String meaning = definition.searchWord(inputString);

      if (meaning.equals("") || inputString.equals("")) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Word not found. Please use Google Translate.");
        alert.show();
        setHtml("");
        htmlToWebview(htmlEditor);
      } else {
        setHtml(meaning);
        htmlToWebview(htmlEditor);
        Historybase.insertWordtoHistory(inputString);

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
   * Sepak the text in inputText.
   */
  public void speakout() {
    inputString = inputText.getText();
    TextToSpeech speech = new TextToSpeech();
    speech.playSound(inputString);
  }

  /**
   * Translate text using Google API.
   */
  public void googleApi() {
    try {
      Parent root1Parent =
          FXMLLoader.load(getClass().getResource("../resources/fxml/GoogleTrans.fxml"));
      Stage newStage = new Stage();
      Image icon = new Image("./resources/icon/googleicon.png");
      newStage.getIcons().add(icon);
      Scene scene1 = new Scene(root1Parent);
      scene1.getStylesheets()
          .add(getClass().getResource("../resources/fxml/GoogleTrans.css").toExternalForm());
      newStage.setTitle("Google Translate");
      newStage.setResizable(false);
      newStage.setScene(scene1);
      newStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Clear user input and definition.
   */
  public void xButtonClicked() {
    inputText.setText("");
    setHtml("");
    htmlToWebview(htmlEditor);
  }

  /**
   * Button to add/delete/modify database.
   */
  public void editButton() {
    try {

      Parent root1Parent =
          FXMLLoader.load(getClass().getResource("../resources/fxml/EditDatabase.fxml"));
      Stage newStage = new Stage();
      Image icon = new Image("./resources/icon/editicon.png");
      newStage.getIcons().add(icon);
      Scene scene1 = new Scene(root1Parent);
      scene1.getStylesheets()
          .add(getClass().getResource("../resources/fxml/EditDatabase.css").toExternalForm());
      newStage.setTitle("Database Manipulation.");
      newStage.setResizable(false);
      newStage.setScene(scene1);
      newStage.initModality(Modality.APPLICATION_MODAL);
      newStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Export all database to CSV file.
   */
  public void exportButton() throws FileNotFoundException {

    Alert alert6 = new Alert(AlertType.CONFIRMATION);

    alert6.setHeaderText("Do you really want to export this dictionary?");

    Optional<ButtonType> result = alert6.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {

      try {
        final Database exDatabase = new Database();
        exDatabase.exportDataToCsv();
        Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
        alert5.setContentText("Export complete.");
        alert5.show();

      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Display helps.
   */
  public void helpButtonClicked() {
    Alert alert7 = new Alert(AlertType.INFORMATION);
    alert7.setWidth(450);
    alert7.setHeight(500);

    alert7.setHeaderText("HELPS!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    alert7.setTitle("Helps.");
    alert7.setContentText(
        "-Text your word in the search field and click Search to find word.\n\n-Click speak button to hear the word pronunciation.\n\n-Click Google translate button to use google translate if the word is not found or you want to translate a paragraph.\n\n-Click Edit Word button to add, delete or modify definition of the word.\n\n-Click Export in the top right of the screen to export file to .csv file.\n\n-Click history button on the bottom right of the screen to get recently searched words.");
    alert7.show();
  }

  /**
   * Display history.
   */
  public void historyButtonClicked() {
    Historybase.getHistoryData();
    try {
      Parent root1Parent =
          FXMLLoader.load(getClass().getResource("../resources/fxml/History.fxml"));
      Stage newStage = new Stage();
      Image icon = new Image("./resources/icon/historyicon.png");
      newStage.getIcons().add(icon);
      Scene scene1 = new Scene(root1Parent);
      newStage.setTitle("History.");
      newStage.setResizable(false);
      newStage.setScene(scene1);
      newStage.initModality(Modality.APPLICATION_MODAL);
      newStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
