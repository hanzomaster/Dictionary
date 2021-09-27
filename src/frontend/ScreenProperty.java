package frontend;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.mashape.unirest.http.exceptions.UnirestException;

import backend.api.TranslateApi;
import backend.dictionary.TextToSpeech;

public class ScreenProperty implements Initializable {
  // public TextField height ;
  public TextField inputText;
  public Label label = new Label();
  public String inputString = new String();

  public void submit(ActionEvent event) {

    // TranslateApi newTrans = new TranslateApi();
    inputString = inputText.getText();
    // String VNtrans = "";
    label.setText(inputString);
    /*
     * try { // VNtrans = newTrans.translate(inputString); } catch (UnirestException
     * e) { // TODO Auto-generated catch block e.printStackTrace();
     * System.out.println("Out of network"); }
     */
    /*
     * Alert alert = new Alert(Alert.AlertType.INFORMATION);
     * alert.setContentText("Word :" + inputString); alert.show();
     */
    // TextToSpeech speech = new TextToSpeech();
    // speech.playSound(inputString);

  }

  public void speakout(ActionEvent event) {
    TextToSpeech speech = new TextToSpeech();
    speech.playSound(inputString);
  }

  public void initialize(URL Location, ResourceBundle resources) {
  }

  /*
   * public ComboBox<String> comboBox; public Label label = new Label();
   * ObservableList<String> list = FXCollections.observableArrayList("VI-ENG",
   * "ENG-VI");
   * 
   * public void initialize(URL Location, ResourceBundle resources) {
   * comboBox.setItems(list); }
   * 
   * public void comboBoxChanged(ActionEvent event) {
   * label.setText(comboBox.getValue()); }
   */
}
