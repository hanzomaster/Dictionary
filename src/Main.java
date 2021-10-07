
import backend.MyLogger;
import backend.dictionary.WordSuggestion;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
  /**
   * Click Run to run the application.
   */
  public static void main(String[] args) {
    MyLogger.init();
    try {
      WordSuggestion.parseDataToArrayList();
    } catch (SQLException e1) {
      e1.printStackTrace();
      MyLogger.getLogger().log(Level.WARNING, null, e1);
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
      Parent root = FXMLLoader
          .load(getClass().getClassLoader().getResource("resources/fxml/Controller.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets()
          .add(getClass().getResource("resources/fxml/Application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.setOnCloseRequest(arg0 -> {
        MyLogger.getLogger().info("End of program");
        Platform.exit();
        System.exit(0);
      });
    } catch (Exception e) {
      e.printStackTrace();
      MyLogger.getLogger().log(Level.WARNING, null, e);
    }
  }
}
