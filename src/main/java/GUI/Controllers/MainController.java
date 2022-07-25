package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public StackPane viewStack;
    @FXML
    public Button searchVocabButton;
    @FXML
    public Button translateTextButton;

    public void searchVocabButton_OnClicked(ActionEvent actionEvent) throws IOException {
        searchVocabButton.setStyle("-fx-background-color: #3c3f41; ");
        translateTextButton.setStyle("-fx-background-color: #4b4b4c; ");
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SearchVocabView.fxml"));
        viewStack.getChildren().removeAll();
        viewStack.getChildren().setAll(root);
    }

    public void translateTextButton_OnClicked(ActionEvent actionEvent) throws IOException {
        searchVocabButton.setStyle("-fx-background-color:  #4b4b4c; ");
        translateTextButton.setStyle("-fx-background-color: #3c3f41; ");
        Parent root = FXMLLoader.load(getClass().getResource("/Views/TranslateTextView.fxml"));
        viewStack.getChildren().removeAll();
        viewStack.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            searchVocabButton.setStyle("-fx-background-color: #3c3f41; ");
            translateTextButton.setStyle("-fx-background-color: #4b4b4c; ");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/SearchVocabView.fxml")));
            viewStack.getChildren().removeAll();
            viewStack.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
