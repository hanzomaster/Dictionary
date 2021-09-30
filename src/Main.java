
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Start Application.
   */
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Dictionary");
    primaryStage.setResizable(false);
    Image icon = new Image(new File("./resources/icon/photodemo.png").toURI().toString());
    primaryStage.getIcons().add(icon);
    try {
      Parent root = FXMLLoader
          .load(getClass().getClassLoader().getResource("resources/fxml/Controller.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets()
          .add(getClass().getResource("resources/fxml/Application.css").toExternalForm());
      /*
       * StackPane layout =new StackPane(); Scene scene =new Scene(layout,300,250);
       */
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
