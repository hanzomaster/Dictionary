package frontend;

import backend.database.Historybase;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class HistoryController implements Initializable {

  public ListView<String> historyList;
  public TextField chooseField;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    historyList.getItems().addAll(Historybase.getHistorySet());
    historyList.getSelectionModel().selectedItemProperty()
        .addListener((ChangeListener<String>) (arg0, arg1, arg2) -> chooseField
            .setText(historyList.getSelectionModel().getSelectedItem()));

  }

  /**
   * Copy selected word in ListView to your clipboard.
   */
  public void copyToClipboard() {
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    content.putString(chooseField.getText());
    clipboard.setContent(content);

  }

  /**
   * Delete all history.
   */
  public void clearHistoryButton(ActionEvent event) {

    Alert alert6 = new Alert(AlertType.CONFIRMATION);

    alert6.setHeaderText("Do you really want to clear search history?");

    Optional<ButtonType> result = alert6.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {

      Historybase.clearTheFile();
      ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
      Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
      alert5.setContentText("Cleared.");
      alert5.show();

    }
  }
}
