package GUI.Controllers;

import GUI.Services.Api.ApiCognitiveMicrosoftSpeechToTextService;
import GUI.Services.Api.ApiCognitiveMicrosoftTextToSpeechService;
import GUI.Services.Api.ApiCognitiveMicrosoftTranslatorService;
import GUI.Services.Api.Language;
import GUI.Services.CheckInternetConnectivity;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class TranslateTextController implements Initializable {

    @FXML
    public Button leftListenButton;
    @FXML
    public Button rightListenButton;
    @FXML
    public Button speakButton;

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
    private SingleSelectionModel<Language> srcLanguage;
    private SingleSelectionModel<Language> desLanguage;
    private Property<Boolean> showSwapButton;
    private Property<Boolean> showSpeakButton;
    private Property<Boolean> isSpeaking;
    private String textTranslated;
    private final ApiCognitiveMicrosoftTranslatorService translatorService;
    private final ApiCognitiveMicrosoftTextToSpeechService textToSpeechService;
    private final ApiCognitiveMicrosoftSpeechToTextService speechToTextService;


    public TranslateTextController() {
        translatorService = new ApiCognitiveMicrosoftTranslatorService();
        textToSpeechService = new ApiCognitiveMicrosoftTextToSpeechService();
        speechToTextService = new ApiCognitiveMicrosoftSpeechToTextService();
        showSwapButton = new SimpleBooleanProperty(true);
        showSpeakButton = new SimpleBooleanProperty(true);
        isSpeaking = new SimpleBooleanProperty(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            speakButton.disableProperty().bindBidirectional(isSpeaking);
            speakButton.visibleProperty().bindBidirectional(showSpeakButton);
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
                if (leftComboBox.getValue().getName().equals("Tiếng Anh")) {
                    showSpeakButton.setValue(true);
                } else {
                    showSpeakButton.setValue(false);
                }
                translate();
            });
            rightComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                translate();
            });
            leftTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
                if (leftTextArea.getText().length() < 100) {
                    leftTextArea.setFont(new Font("Times New Roman", 34));
                    rightTextArea.setFont(new Font("Times New Roman", 34));
                } else {
                    leftTextArea.setFont(new Font("Times New Roman", 22));
                    rightTextArea.setFont(new Font("Times New Roman", 22));
                }
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
        if (!CheckInternetConnectivity.IsConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Đã có lỗi xảy ra khi thực hiện phát âm, hãy thử kiểm tra kết nối internet.", ButtonType.OK);
            alert.setTitle("Error.");
            alert.setHeaderText("Đã có lỗi xảy ra.");
            alert.showAndWait();
            return;
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    textToSpeechService.textToSpeech(text, languageAcronym.equals(Language.Auto_Detect) ? Language.English : languageAcronym);
                } catch (ExecutionException | InterruptedException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();
    }

    public void translateButton_OnClicked(ActionEvent actionEvent) {

        translate();
    }

    public void speakButton_OnClicked(ActionEvent actionEvent) {
        if (!CheckInternetConnectivity.IsConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Đã có lỗi xảy ra khi dịch từ giọng nói, hãy thử kiểm tra kết nối internet.", ButtonType.OK);
            alert.setTitle("Error.");
            alert.setHeaderText("Đã có lỗi xảy ra.");
            alert.showAndWait();
            return;
        }
        isSpeaking.setValue(true);
        leftTextArea.setText("Đang nghe...");
        rightTextArea.setText("");
        translateFromSpeech();
    }
    private void translate() {
        if (!CheckInternetConnectivity.IsConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Đã có lỗi xảy ra khi dịch văn bản, hãy thử kiểm tra kết nối internet.", ButtonType.OK);
            alert.setTitle("Error.");
            alert.setHeaderText("Đã có lỗi xảy ra.");
            alert.showAndWait();
            return;
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                textTranslated = translatorService.translate(leftTextArea.getText(), leftComboBox.getValue().getAcronym(), rightComboBox.getValue().getAcronym());
                rightTextArea.setText(textTranslated);
            }
        };
        if (leftTextArea.getText().length() < 100) {
            leftTextArea.setFont(new Font("Times New Roman", 34));
            rightTextArea.setFont(new Font("Times New Roman", 34));
        } else {
            leftTextArea.setFont(new Font("Times New Roman", 22));
            rightTextArea.setFont(new Font("Times New Roman", 22));
        }
        isSpeaking.setValue(false);
        thread.start();
    }
    private void translateFromSpeech() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    leftTextArea.setText(speechToTextService.recognizeFromMicrophone());
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                }
                translate();
            }
        };
        thread.start();
    }
}
