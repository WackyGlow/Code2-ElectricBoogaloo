package PMC.gui;

import PMC.be.Genre;
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

public class EditGenreViewController implements Initializable {

    @FXML
    public TextField editGenreName;
    @FXML
    public Button editGenreCreate;
    @FXML
    public Button editGenreCancel;

    private Genre selectedGenre;
    private GenreModel genreModel;

    /**
     * Handles the EditGenreCreate action connected to the GUI.
     * @param actionEvent
     * @throws SQLException
     */
    public void handleEditGenreCreate(ActionEvent actionEvent) throws SQLException {
        selectedGenre = Controller.getSelectedGenre();
        genreModel.updateGenre(selectedGenre,editGenreName.getText());
        Stage stage = (Stage) editGenreCreate.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the EditGenreCancel action connected to the GUI.
     * @param actionEvent
     */
    public void handleEditGenreCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) editGenreCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the process of creating a new genre within the GUI.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            genreModel = new GenreModel();
            editGenreName.setText(Controller.getEditGenreName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
