package PMC.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewGenreViewController implements Initializable {

    public TextField newGenreName;
    private GenreModel genreModel;

    @FXML
    public Button newGenreCreate;
    @FXML
    public Button newGenreCancel;

    public void handleNewGenreCreate(ActionEvent actionEvent) throws SQLException {
        genreModel.createGenre(newGenreName.getText());
        Stage stage = (Stage) newGenreCreate.getScene().getWindow();
        stage.close();
    }

    public void handleNewGenreCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) newGenreCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            genreModel = new GenreModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
