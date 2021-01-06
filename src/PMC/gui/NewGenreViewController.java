package PMC.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewGenreViewController {
    @FXML
    public Button newGenreCreate;
    @FXML
    public Button newGenreCancel;

    public void handleNewGenreCreate(ActionEvent actionEvent) {

    }

    public void handleNewGenreCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) newGenreCancel.getScene().getWindow();
        stage.close();
    }
}
