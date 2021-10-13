package frontend;

import backend.api.GoogleApi;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GoogleTransControl {
  public TextArea inputArea;
  public WebView viewer;


  /**
   * Translate from English to Vietnamese.
   */
  public void translateEtoVButtonClicked() {
    String inputString = inputArea.getText();

    String translateText;
    try {
      WebEngine engine = viewer.getEngine();
      translateText = GoogleApi.translateEnToVi(inputString);
      engine.loadContent(translateText);
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
      WebEngine engine = viewer.getEngine();
      String translateText = GoogleApi.translateViToEn(inputString);
      engine.loadContent(translateText);
    } catch (IOException e) {
      Alert alert7 = new Alert(AlertType.INFORMATION);
      alert7.setHeaderText("Word not found");
      alert7.setTitle("ERROR");
      alert7.show();

      e.getMessage();
    }
  }
}
