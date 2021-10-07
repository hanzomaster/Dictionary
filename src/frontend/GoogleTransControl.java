package frontend;

import backend.api.GoogleApi;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class GoogleTransControl {
  public TextArea inputArea;
  public TextArea translateArea;

  /**
   * Translate from English to Vietnamese.
   */
  public void translateEtoVButtonClicked() {
    String inputString = inputArea.getText();

    String translateText;
    try {
      translateText = GoogleApi.translateEnToVi(inputString);
      translateArea.setText(translateText);
    } catch (IOException e) {
      Alert alert7 = new Alert(AlertType.INFORMATION);
      alert7.setHeaderText("Word not found");
      alert7.setTitle("ERROR");
      alert7.show();

      e.getMessage();
    }
  }

  /**
   * Translate from Vietnamese to English.
   */
  public void translateVtoEButtonClicked() {
    String inputString = inputArea.getText();
    try {
      String translateText = GoogleApi.translateViToEn(inputString);
      translateArea.setText(translateText);
    } catch (IOException e) {
      Alert alert7 = new Alert(AlertType.INFORMATION);
      alert7.setHeaderText("Word not found");
      alert7.setTitle("ERROR");
      alert7.show();

      e.getMessage();
    }
  }
}
