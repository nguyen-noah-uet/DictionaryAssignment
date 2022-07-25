package GUI.Controllers;

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


    public TranslateTextController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            leftComboBox.getItems().addAll(Language.getSrcLanguage());
            rightComboBox.getItems().addAll(Language.getDesLanguage());
            Language english = leftComboBox.getItems().filtered(x -> x.getName().equals("English")).stream().findFirst().get();
            Language vietnamese = rightComboBox.getItems().filtered(x -> x.getName().equals("Vietnamese")).stream().findFirst().get();
            leftComboBox.setValue(english);
            rightComboBox.setValue(vietnamese);
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
        leftTextArea.setText(rightTextArea.getText());
    }

    public void leftListenButton_OnClicked(ActionEvent actionEvent) {
    }

    public void rightListenButton_Onclicked(ActionEvent actionEvent) {
    }
}
