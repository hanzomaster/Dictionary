
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
  Stage Window;
  Button button = new Button();
  Button button2 = new Button();
  Scene scene1;

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage Primarystage) {
    Primarystage.setTitle("Dictionary");
    try {
      FXMLLoader loader = new FXMLLoader();
      Parent root = loader.load(this.getClass().getResource("frontend/Controller.fxml"));
      // loader.setController("ScreenProperty");
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("frontend/Application.css").toExternalForm());
      /*
       * StackPane layout =new StackPane(); Scene scene =new Scene(layout,300,250);
       */
      Primarystage.setScene(scene);
      Primarystage.show();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  /*
   * Primarystage.setTitle("Hello World"); FXMLLoader loader = new FXMLLoader();
   * loader.setLocation(getClass().getResource("Controller.fxml")); Parent content
   * = loader.load(); Scene scene = new Scene(content,400,600); VBox layout = new
   * VBox(); button.setText("Search"); Scene scene = new Scene(layout,300,250);
   * Label label = new Label(); label.setText("Welcome to my dictionary.");
   * layout.getChildren().addAll(label,button);
   * 
   * //scene 1 button.setOnAction(new EventHandler<ActionEvent>(){ public void
   * handle(ActionEvent event) { Primarystage.setScene(scene1);; } }); StackPane
   * layout1 = new StackPane(); button2.setText("Go back");
   * layout1.getChildren().add(button2); scene1 = new Scene(layout1,500,300);
   * button2.setOnAction(new EventHandler<ActionEvent>(){ public void
   * handle(ActionEvent event) { Alert alert = new
   * Alert(Alert.AlertType.CONFIRMATION);
   * alert.setHeaderText("Alert Information");
   * alert.setContentText("Choose your option"); alert.setTitle("Confirmation");
   * 
   * ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
   * alert.getButtonTypes().addAll(buttonTypeYes); Optional<ButtonType> result =
   * alert.showAndWait(); if(result.get() == buttonTypeYes )
   * System.out.println("Hello"); String message = result.get().getText(); Alert
   * alert1 = new Alert(Alert.AlertType.INFORMATION);
   * alert1.setHeaderText("Information"); alert1.setTitle("Notification");
   * alert1.setContentText(message); alert1.show();
   * 
   * } }); Primarystage.setScene(scene); Primarystage.show();
   */
}
