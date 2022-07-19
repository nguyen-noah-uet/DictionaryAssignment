package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiApp extends Application {
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/MainView.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Từ điển Anh-Việt");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
