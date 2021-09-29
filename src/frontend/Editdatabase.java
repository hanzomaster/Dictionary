package frontend;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Editdatabase {
    private Pane view;

    public void loadWindow() {

        try {
            FXMLLoader loader = new FXMLLoader();
            // ResourceBundle resource = ResourceBundle.getBundle("Language/lang_pt");
            view =
                    // FXMLLoader.load(getClass().getResource("./resources/fxml/Controller.fxml"),
                    // resource);
                    loader.load(getClass().getResource("./resources/fxml/EditDatabase.fxml"));
            // loader.setController("ScreenProperty");
            /*
             * scene.getStylesheets()
             * .add(getClass().getResource("./resources/fxml/Application.css").toExternalForm());
             */
            /*
             * StackPane layout =new StackPane(); Scene scene =new Scene(layout,300,250);
             */
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
