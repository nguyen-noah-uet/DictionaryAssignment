package GUI.Controllers;

import GUI.Entities.Vocabulary;
import GUI.Services.Api.ApiCognitiveMicrosoftTranslatorService;
import GUI.Services.Data.DictionaryDataService;
import GUI.Services.Data.IDataService;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class SearchVocabController implements Initializable {

    @FXML
    public TextField searchTextField;
    @FXML
    public ListView<Vocabulary> vocabularyList;
    @FXML
    public WebView webView;
    @FXML
    public HBox bottomHBox;
    @FXML
    public Button modifyButton;
    @FXML
    public Button deleteButton;
    @FXML
    public HBox topHBox;
    @FXML
    public Button pronounceButton;
    private final IDataService<Vocabulary> dataService;
    private final ApiCognitiveMicrosoftTranslatorService translatorService;
    private Vocabulary selectedVocabulary;
    public Vocabulary getSelectedVocabulary() {
        return selectedVocabulary;
    }

    public void setSelectedVocabulary(Vocabulary selectedVocabulary) {
        this.selectedVocabulary = selectedVocabulary;
    }
    public SearchVocabController() {
        dataService = new DictionaryDataService();
        translatorService = new ApiCognitiveMicrosoftTranslatorService();
        vocabularyList = new ListView<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchTerm = newValue != null ? newValue : "";
            updateVocabularyList(searchTerm);
            vocabularyList.getSelectionModel().select(0);
        });
        vocabularyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        vocabularyList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedVocabulary = newValue;
            if (selectedVocabulary == null) {
                return;
            }
            webView.getEngine().loadContent(selectedVocabulary.getDetailString());
            webView.setFontScale(1.2);
        });
        updateVocabularyList(null);
    }

    private void updateVocabularyList(String searchTerm) {
        vocabularyList.getItems().clear();
        if (searchTerm == null || searchTerm == "") {
            vocabularyList.getItems().addAll(dataService.getWords(0, 50));
            vocabularyList.setVisible(true);
            return;
        }
        vocabularyList.getItems().addAll(dataService.findWord(searchTerm));
    }


}
