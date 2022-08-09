package GUI.Controllers;

import GUI.Entities.Vocabulary;
import GUI.Services.Data.IDataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewVocabController implements Initializable {
    @FXML
    public TextField newVocabTextField;
    @FXML
    public TextField phoneticTextField;
    @FXML
    public ComboBox<String> wordTypeComboBox1;
    @FXML
    public TextArea wordMeaning1;
    @FXML
    public ComboBox<String> wordTypeComboBox2;
    @FXML
    public ComboBox<String> wordTypeComboBox3;
    @FXML
    public TextArea wordMeaning3;
    @FXML
    public TextArea wordMeaning2;
    @FXML
    public WebView webView;
    @FXML
    public Button submitButton;
    @FXML
    public Button goBackButton;
    private IDataService<Vocabulary> dataService;
    private final ArrayList<String> wordTypes = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDataService(IDataService<Vocabulary> dataService) {
        this.dataService = dataService;
    }

    public void submitButton_OnClicked(ActionEvent actionEvent) throws IOException {
        MainController.getMainController().navigateToSearchVocabView();
    }

    public void goBackButton_OnClicked(ActionEvent actionEvent) throws IOException {
        MainController.getMainController().navigateToSearchVocabView();
    }
}
