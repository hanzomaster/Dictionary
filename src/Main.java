
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
  Stage window;
  Button button = new Button();
  Button button2 = new Button();
  Scene scene1;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Start Application.
   */
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Dictionary");
    primaryStage.setResizable(false);
    try {
      // ResourceBundle resource = ResourceBundle.getBundle("Language/lang_pt");
      Parent root =
          // FXMLLoader.load(getClass().getResource("./resources/fxml/Controller.fxml"), resource);
          FXMLLoader.load(getClass().getResource("./resources/fxml/Controller.fxml"));
      // loader.setController("ScreenProperty");
      Scene scene = new Scene(root);
      scene.getStylesheets()
          .add(getClass().getResource("./resources/fxml/Application.css").toExternalForm());
      /*
       * StackPane layout =new StackPane(); Scene scene =new Scene(layout,300,250);
       */
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
