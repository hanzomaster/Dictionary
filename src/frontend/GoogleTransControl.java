package frontend;

import backend.api.GoogleApi;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class GoogleTransControl {
    public TextArea inputArea;
    public TextArea translateArea;

    public void translateEtoVButtonClicked(ActionEvent event) {
        String inputString = inputArea.getText();
        try {
            String translateText = GoogleApi.translateEnToVi(inputString);
            translateArea.setText(translateText);
        } catch (Exception e) {
            Alert alert7 = new Alert(AlertType.INFORMATION);
            alert7.setHeaderText("Word not found");
            alert7.setTitle("ERROR");
            alert7.show();
        }
    }

    public void translateVtoEButtonClicked(ActionEvent event) {
        String inputString = inputArea.getText();
        try {
            String translateText = GoogleApi.translateViToEn(inputString);
            translateArea.setText(translateText);
        } catch (Exception e) {
            Alert alert7 = new Alert(AlertType.INFORMATION);
            alert7.setHeaderText("Word not found");
            alert7.setTitle("ERROR");
            alert7.show();
        }
    }
}
