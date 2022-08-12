package GUI.Controllers;

import GUI.Entities.Vocabulary;
import GUI.Services.Data.IDataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final MainController _mainController = new MainController();
    @FXML
    public StackPane viewStack;
    @FXML
    public Button searchVocabButton;
    @FXML
    public Button translateTextButton;
    private MainController(){

    }
    public static MainController getMainController() {
        return _mainController;
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
    public void translateTextButton_OnClicked(ActionEvent actionEvent) throws IOException {
        navigateToTranslateTextView();
    }

    public void navigateToTranslateTextView() throws IOException {
        searchVocabButton.setStyle("-fx-background-color:  #4b4b4c;  -fx-cursor: hand;");
        searchVocabButton.fontProperty().set(new Font("System", 17));
        translateTextButton.setStyle("-fx-background-color: #3c3f41;  -fx-cursor: hand;");
        translateTextButton.fontProperty().set(new Font("System Bold", 18));
        Parent root = FXMLLoader.load(getClass().getResource("/Views/TranslateTextView.fxml"));
        viewStack.getChildren().removeAll();
        viewStack.getChildren().setAll(root);
    }
    public void searchVocabButton_OnClicked(ActionEvent actionEvent) throws IOException {
        navigateToSearchVocabView();
    }
    public void navigateToSearchVocabView() throws IOException {
        searchVocabButton.setStyle("-fx-background-color: #3c3f41;  -fx-cursor: hand;");
        searchVocabButton.fontProperty().set(new Font("System Bold", 18));
        translateTextButton.setStyle("-fx-background-color: #4b4b4c;  -fx-cursor: hand;");
        translateTextButton.fontProperty().set(new Font("System", 17));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/SearchVocabView.fxml"));
        Parent root = fxmlLoader.load();
        SearchVocabController searchVocabController = fxmlLoader.getController();

        viewStack.getChildren().removeAll();
        viewStack.getChildren().setAll(root);
    }

    public void navigateToEditVocabView(Vocabulary vocabulary, IDataService<Vocabulary> dataService) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/EditVocabView.fxml"));
        Parent root = fxmlLoader.load();
        EditVocabController editVocabController = fxmlLoader.getController();
        editVocabController.setVocabulary(vocabulary);
        editVocabController.setDataService(dataService);
        viewStack.getChildren().removeAll();
        viewStack.getChildren().setAll(root);
    }

    public void navigateToAddNewVocabView(IDataService<Vocabulary> dataService) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddNewVocabView.fxml"));
        Parent root = fxmlLoader.load();
        AddNewVocabController addNewVocabController = fxmlLoader.getController();
        addNewVocabController.setDataService(dataService);
        viewStack.getChildren().removeAll();
        viewStack.getChildren().setAll(root);
    }
}
