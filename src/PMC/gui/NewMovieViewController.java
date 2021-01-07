package PMC.gui;

import PMC.be.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewMovieViewController implements Initializable {

    private MovieModel movieModel;


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

    public void handleNewMovieCreate(ActionEvent actionEvent) {
        //movieModel.createMovie(newMovieName.getText(),newMovieImdbRating.getText(),newMoviePersonalRating.getText(),newMovieFilepath,);
    }

    public void handleNewMovieCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) newMovieCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieModel = new MovieModel();
    }
}
