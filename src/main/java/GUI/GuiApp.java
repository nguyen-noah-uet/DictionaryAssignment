package GUI;

import GUI.Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiApp extends Application {
    private MainController mainController;

    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/MainView.fxml"));
        Parent root = fxmlLoader.load();
        mainController = fxmlLoader.getController();
        primaryStage.getIcons().add(new Image("Image/EnglishIcon.png"));
        primaryStage.setTitle("Từ điển Anh-Việt");
        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(650);
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }
}
