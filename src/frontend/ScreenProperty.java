package frontend;

import backend.dictionary.TextToSpeech;
import java.net.URL;
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

  /**
   * Submit text to translate.
   * 
   * @param event take event
   */
  public void submit(ActionEvent event) {

    // TranslateApi newTrans = new TranslateApi();
    inputString = inputText.getText();
    // String VNtrans = "";
    label.setText(inputString);
    /*
     * try { // VNtrans = newTrans.translate(inputString); } catch (UnirestException e) {
     * e.printStackTrace(); System.out.println("Out of network"); }
     */
    /*
     * Alert alert = new Alert(Alert.AlertType.INFORMATION); alert.setContentText("Word :" +
     * inputString); alert.show();
     */
    // TextToSpeech speech = new TextToSpeech();
    // speech.playSound(inputString);

  }

  public void speakout(ActionEvent event) {
    TextToSpeech speech = new TextToSpeech();
    speech.playSound(inputString);
  }

  public void initialize(URL location, ResourceBundle resources) {}

  /*
   * public ComboBox<String> comboBox; public Label label = new Label(); ObservableList<String> list
   * = FXCollections.observableArrayList("VI-ENG", "ENG-VI");
   * 
   * public void initialize(URL Location, ResourceBundle resources) { comboBox.setItems(list); }
   * 
   * public void comboBoxChanged(ActionEvent event) { label.setText(comboBox.getValue()); }
   */
}
