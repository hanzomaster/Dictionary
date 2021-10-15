package frontend;

import backend.database.Database;
import backend.dictionary.WordSuggestion;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;

public class Editdatabase implements Initializable {
  public TextField word;
  public HTMLEditor detailEditor;

  private String inputWord = "";
  private String inputDefinition = "";

  private static final String ERROR_STRING = "ERROR";
  private static final String SUCCESSFUL_STRING = "SUCCESSFULLY";

  private Set<String> newSuggestedWord = new HashSet<>();
  static SuggestionProvider<String> provider;

  /**
   * Initialize autocompletion when input text.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    provider = SuggestionProvider.create(WordSuggestion.getSuggestedWords());
    new AutoCompletionTextFieldBinding<>(word, provider);
  }

  public void getTextinEditField(KeyEvent event) {
    String newText = word.getText();

    for (String relateword : WordSuggestion.getSuggestedWords()) {
      if (relateword.startsWith(newText) && !newText.equals("")) {
        newSuggestedWord.add(relateword);
      }

    }

    // https://stackoverflow.com/questions/45778462/update-autocomplete-javafx
    provider.clearSuggestions();
    provider.addPossibleSuggestions(newSuggestedWord);
    newSuggestedWord.clear();
  }

  /**
   * Add new word and definition to database.
   */
  public void addButtonClicked() {

    inputWord = word.getText();

    inputDefinition = detailEditor.getHtmlText();

    if (detailEditor.getHtmlText()
        .equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
      inputDefinition = "";
    }

    if (inputWord.equals("") || inputDefinition.equals("")) {
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

      alert1.setContentText("Error. Please check your input.");
      alert1.setTitle(ERROR_STRING);

      alert1.show();
    } else {
      try {
        final Database wordAdd = new Database();

        if (!wordAdd.searchWord(inputWord).equals("")) {
          Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

          alert2.setContentText("Error. Your word has already been in this dictionary.");
          alert2.setTitle(ERROR_STRING);

          alert2.show();
        } else {
          // https://stackoverflow.com/questions/45778462/update-autocomplete-javafx
          WordSuggestion.getSuggestedWords().add(inputWord);
          ScreenProperty.provider.clearSuggestions();
          ScreenProperty.provider.addPossibleSuggestions(WordSuggestion.getSuggestedWords());

          wordAdd.insertNewWord(inputWord, inputDefinition);

          Alert alert = new Alert(Alert.AlertType.INFORMATION);

          alert.setContentText("Word add successfully.");

          alert.setTitle(SUCCESSFUL_STRING);

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
  public void deleteButtonClicked() {
    inputWord = word.getText();
    try {
      WordSuggestion.getSuggestedWords().remove(inputWord);
      ScreenProperty.provider.clearSuggestions();
      ScreenProperty.provider.addPossibleSuggestions(WordSuggestion.getSuggestedWords());

      final Database wordDelete = new Database();

      wordDelete.deleteWord(inputWord);

      Alert alert3 = new Alert(Alert.AlertType.INFORMATION);

      alert3.setContentText("Word delete successfully.");

      alert3.setTitle(SUCCESSFUL_STRING);

      alert3.show();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Modify word definiton in database.
   */
  public void modifyButtonClicked() {
    inputWord = word.getText();
    inputDefinition = detailEditor.getHtmlText();

    if (detailEditor.getHtmlText()
        .equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
      inputDefinition = "";
    }

    try {
      final Database wordModify = new Database();

      if (inputWord.equals("") || inputDefinition.equals("")) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        alert1.setContentText("Error. Please check your input.");
        alert1.setTitle(ERROR_STRING);

        alert1.show();
      } else if (wordModify.searchWord(inputWord).equals("")) {
        Alert alert4 = new Alert(Alert.AlertType.INFORMATION);

        alert4.setContentText("Error. This word has not been existed.");

        alert4.setTitle(ERROR_STRING);

        alert4.show();
      } else {

        wordModify.updateWordDefinition(inputWord, inputDefinition);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);

        alert3.setContentText("Word definition change successfully.");

        alert3.setTitle(SUCCESSFUL_STRING);

        alert3.show();
        System.out.println(inputDefinition.length());
        System.out.println(inputDefinition);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
