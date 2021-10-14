package frontend;

import backend.database.Historybase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class HistoryController implements Initializable {

  public ListView<String> historyList;
  public TextField chooseField;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    historyList.getItems().addAll(Historybase.getHistorySet());
    historyList.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<String>() {

          @Override
          public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
            chooseField.setText(historyList.getSelectionModel().getSelectedItem());
          }

        });

  }

  public void copyToClipboard() {
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    content.putString(chooseField.getText());
    clipboard.setContent(content);

  }

}
