package GUI.Controllers;

import GUI.Entities.Vocabulary;
import GUI.Services.Data.IDataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
        addWordTypes();
        addListener();
    }
    private void addWordTypes() {
        wordTypes.add("Danh từ");
        wordTypes.add("Động từ");
        wordTypes.add("Tính từ");
        wordTypes.add("Trạng từ");
        wordTypeComboBox1.getItems().addAll(wordTypes);
        wordTypeComboBox2.getItems().addAll(wordTypes);
        wordTypeComboBox3.getItems().addAll(wordTypes);
    }
    private void addListener() {
        newVocabTextField.textProperty().addListener((observable, oldValue, newValue) -> webView.getEngine().loadContent(generateHtml()));
        phoneticTextField.textProperty().addListener((observable, oldValue, newValue) -> webView.getEngine().loadContent(generateHtml()));
        wordTypeComboBox1.valueProperty().addListener((observable, oldValue, newValue) -> webView.getEngine().loadContent(generateHtml()));
        wordTypeComboBox2.valueProperty().addListener((observable, oldValue, newValue) -> webView.getEngine().loadContent(generateHtml()));
        wordTypeComboBox3.valueProperty().addListener((observable, oldValue, newValue) -> webView.getEngine().loadContent(generateHtml()));
        wordMeaning1.textProperty().addListener((observable, oldValue, newValue) -> webView.getEngine().loadContent(generateHtml()));
        wordMeaning2.textProperty().addListener((observable, oldValue, newValue) -> webView.getEngine().loadContent(generateHtml()));
        wordMeaning3.textProperty().addListener((observable, oldValue, newValue) -> webView.getEngine().loadContent(generateHtml()));
    }

    private String generateHtml() {
        StringBuilder result = new StringBuilder(String.format("<h1>%s</h1>", newVocabTextField.getText()));
        if (!phoneticTextField.getText().isEmpty()) {
            result.append(String.format("<h3><i>/%s/</i></h3>", phoneticTextField.getText()));
        }
        if (wordTypeComboBox1.getValue() != null && !wordMeaning1.getText().isEmpty()) {
            result.append(String.format("<h2>%s</h2>", wordTypeComboBox1.getValue()));
            String[] lines = wordMeaning1.getText().split("\n");
            result.append("<ul>");
            for (String line : lines) {
                result.append(String.format("<li>%s</li>", line));
            }
            result.append("</ul>");
        }
        if (wordTypeComboBox2.getValue() != null && !wordMeaning2.getText().isEmpty()) {
            result.append(String.format("<h2>%s</h2>", wordTypeComboBox2.getValue()));
            String[] lines = wordMeaning2.getText().split("\n");
            result.append("<ul>");
            for (String line : lines) {
                result.append(String.format("<li>%s</li>", line));
            }
            result.append("</ul>");
        }
        if (wordTypeComboBox3.getValue() != null && !wordMeaning3.getText().isEmpty()) {
            result.append(String.format("<h2>%s</h2>", wordTypeComboBox3.getValue()));
            String[] lines = wordMeaning3.getText().split("\n");
            result.append("<ul>");
            for (String line : lines) {
                result.append(String.format("<li>%s</li>", line));
            }
            result.append("</ul>");
        }
        return result.toString();
    }

    public void setDataService(IDataService<Vocabulary> dataService) {
        this.dataService = dataService;
    }

    public void submitButton_OnClicked(ActionEvent actionEvent) throws IOException {
        String englishWord = newVocabTextField.getText();
        if (englishWord.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Phần từ mới không được để trống", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        dataService.add(new Vocabulary(englishWord, generateHtml()));
        MainController.getMainController().navigateToSearchVocabView();
    }

    public void goBackButton_OnClicked(ActionEvent actionEvent) throws IOException {
        MainController.getMainController().navigateToSearchVocabView();
    }
}
