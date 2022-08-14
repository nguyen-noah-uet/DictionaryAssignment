package GUI.Controllers;

import GUI.Services.Api.ApiCognitiveMicrosoftTextToSpeechService;
import GUI.Services.Api.ApiCognitiveMicrosoftTranslatorService;
import GUI.Services.Api.Language;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class TranslateTextController implements Initializable {

    @FXML
    public Button leftListenButton;
    @FXML
    public Button rightListenButton;
    private SingleSelectionModel<Language> srcLanguage;
    private SingleSelectionModel<Language> desLanguage;
    @FXML
    public Button swapButton;
    @FXML
    public TextArea leftTextArea;
    @FXML
    public TextArea rightTextArea;
    @FXML
    public ComboBox<Language> leftComboBox;
    @FXML
    public ComboBox<Language> rightComboBox;

    private final Property<Boolean> showSwapButton = new SimpleBooleanProperty(true);

    private String textTranslated;
    private final ApiCognitiveMicrosoftTranslatorService translatorService;
    private final ApiCognitiveMicrosoftTextToSpeechService textToSpeechService;


    public TranslateTextController() {
        translatorService = new ApiCognitiveMicrosoftTranslatorService();
        textToSpeechService = new ApiCognitiveMicrosoftTextToSpeechService();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            swapButton.visibleProperty().bindBidirectional(showSwapButton);
            leftComboBox.getItems().addAll(Language.getSrcLanguage());
            rightComboBox.getItems().addAll(Language.getDesLanguage());
            Language english = leftComboBox.getItems().filtered(x -> x.getName().equals("Tiếng Anh")).stream().findFirst().get();
            Language vietnamese = rightComboBox.getItems().filtered(x -> x.getName().equals("Tiếng Việt")).stream().findFirst().get();
            leftComboBox.setValue(english);
            rightComboBox.setValue(vietnamese);
            leftComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (leftComboBox.getValue().getName().equals("Tự động phát hiện ngôn ngữ")) {
                    showSwapButton.setValue(false);
                } else {
                    showSwapButton.setValue(true);
                }
                textTranslated = translatorService.translate(leftTextArea.getText(), leftComboBox.getValue().getAcronym(), rightComboBox.getValue().getAcronym());
                rightTextArea.setText(textTranslated);
            });
            rightComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                textTranslated = translatorService.translate(leftTextArea.getText(), leftComboBox.getValue().getAcronym(), rightComboBox.getValue().getAcronym());
                rightTextArea.setText(textTranslated);
            });
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    public void swapButton_OnClicked(ActionEvent actionEvent) {
        String srcLanguageName = leftComboBox.getValue().getName();
        String desLanguageName = rightComboBox.getValue().getName();
        Language src = leftComboBox.getItems().filtered(x -> x.getName().equals(desLanguageName)).stream().findFirst().get();
        Language des = rightComboBox.getItems().filtered(x -> x.getName().equals(srcLanguageName)).stream().findFirst().get();
        leftComboBox.setValue(src);
        rightComboBox.setValue(des);
        leftTextArea.setText("");
        rightTextArea.setText("");
    }

    public void leftListenButton_OnClicked(ActionEvent actionEvent) {
        listen(leftTextArea.getText(), leftComboBox.getValue().getAcronym());
    }

    public void rightListenButton_Onclicked(ActionEvent actionEvent) {
        listen(rightTextArea.getText(), rightComboBox.getValue().getAcronym());
    }

    private void listen(String text,final String languageAcronym) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    textToSpeechService.textToSpeech(text, languageAcronym.equals(Language.Auto_Detect) ? Language.English : languageAcronym);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();
    }

    public void translateButton_OnClicked(ActionEvent actionEvent) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                textTranslated = translatorService.translate(leftTextArea.getText(), leftComboBox.getValue().getAcronym(), rightComboBox.getValue().getAcronym());
                rightTextArea.setText(textTranslated);
            }
        };
        thread.start();
    }
}
