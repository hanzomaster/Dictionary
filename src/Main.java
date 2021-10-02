
import backend.dictionary.WordSuggestion;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
  /**
   * Click Run to run the application.
   */
  public static void main(String[] args) {
    try {
      WordSuggestion.parseDataToArrayList();
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    launch(args);
  }

  /**
   * Start Application.
   */
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Dictionary");
    primaryStage.setResizable(false);
    Image icon = new Image("./resources/icon/photodemo.png");
    primaryStage.getIcons().add(icon);
    try {
      Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/fxml/Controller.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("resources/fxml/Application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent arg0) {
          Platform.exit();
          System.exit(0);
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
