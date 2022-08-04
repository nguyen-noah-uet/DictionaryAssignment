package GUI.Controllers;

import GUI.Entities.Vocabulary;
import GUI.Services.Data.IDataService;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewVocabController implements Initializable {
    private IDataService<Vocabulary> dataService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDataService(IDataService<Vocabulary> dataService) {
        this.dataService = dataService;
    }
}
