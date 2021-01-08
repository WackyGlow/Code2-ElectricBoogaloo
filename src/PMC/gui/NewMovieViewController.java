package PMC.gui;

import PMC.be.Movie;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewMovieViewController implements Initializable {

    private MovieModel movieModel;

    @FXML
    public DatePicker lastViewed;
    @FXML
    public ComboBox newMovieGenre;
    @FXML
    public Button newMovieCancel;
    @FXML
    public Button newMovieCreate;
    @FXML
    public Button filePath;
    @FXML
    public TextField newMovieFilepath;
    @FXML
    public TextField newMoviePersonalRating;
    @FXML
    public TextField newMovieImdbRating;
    @FXML
    public TextField newMovieName;

    private File selectedFile = null;


    public void handleFilePath(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select the movie...");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Movie files", "*.mp4", "*.mpeg4")
        );
        selectedFile = fc.showOpenDialog(newMovieFilepath.getScene().getWindow());
        if (selectedFile != null) {
            newMovieFilepath.setText(selectedFile.getAbsolutePath());
        }
    }

    public void handleNewMovieCreate(ActionEvent actionEvent) throws IOException {
        try {
            movieModel.createMovie(newMovieName.getText(),
                    Integer.parseInt(newMovieImdbRating.getText()),
                    Integer.parseInt(newMoviePersonalRating.getText()),
                    newMovieFilepath.getText(),
                    lastViewed.getValue().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
            Stage stage = (Stage) newMovieCreate.getScene().getWindow();
            stage.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void handleNewMovieCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) newMovieCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            movieModel = new MovieModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLastViewed(ActionEvent actionEvent) {
    }

    public void handleNewMovieGenre(ActionEvent actionEvent) {
    }
}
