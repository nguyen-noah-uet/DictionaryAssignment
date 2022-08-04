package GUI.Controllers;

import GUI.Entities.Vocabulary;
import GUI.Services.Data.IDataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditVocabController implements Initializable {
    private Vocabulary vocabulary;
    private IDataService<Vocabulary> dataService;
    @FXML
    public TextField wordTextField;
    @FXML
    public TextArea meaningTextArea;
    @FXML
    public Button saveButton;
    @FXML
    public Button goBackButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveButton_OnClicked(ActionEvent actionEvent) throws IOException {
        MainController.getMainController().navigateToTranslateTextView();
    }

    public void goBackButton_OnClicked(ActionEvent actionEvent) throws IOException {
        MainController.getMainController().navigateToSearchVocabView();
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public void setDataService(IDataService<Vocabulary> dataService) {
        this.dataService = dataService;
    }
}
