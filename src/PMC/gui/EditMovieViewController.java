package PMC.gui;

import PMC.be.Genre;
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

public class EditMovieViewController implements Initializable {

    private MovieModel movieModel;
    private GenreModel genreModel;
    private ObservableList<Genre> localGenreList;

    @FXML
    public DatePicker lastViewed;
    @FXML
    public ComboBox editMovieGenre;
    @FXML
    public Button editMovieCancel;
    @FXML
    public Button editMovieCreate;
    @FXML
    public Button filePath;
    @FXML
    public TextField editMovieFilepath;
    @FXML
    public TextField editMoviePersonalRating;
    @FXML
    public TextField editMovieImdbRating;
    @FXML
    public TextField editMovieName;

    private Movie selectedMovie;

    private File selectedFile = null;

    /**
     * Handles the file path action connected to the GUI.
     * @param actionEvent
     */
    public void handleFilePath(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select the movie...");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Movie files", "*.mp4", "*.mpeg4")
        );
        selectedFile = fc.showOpenDialog(editMovieFilepath.getScene().getWindow());
        if (selectedFile != null) {
            editMovieFilepath.setText(selectedFile.getAbsolutePath());
        }
    }

    /**
     * Handles the EditMovieCreate action connected to the GUI.
     * @param actionEvent
     * @throws IOException
     */
    public void handleEditMovieCreate(ActionEvent actionEvent) throws IOException {
        try{
            selectedMovie = Controller.getSelectedMovie();
            movieModel.updateMovie(selectedMovie,editMovieName.getText(),
                    Integer.parseInt(editMovieImdbRating.getText()),
                    Integer.parseInt(editMoviePersonalRating.getText()),
                    editMovieFilepath.getText(),
                    lastViewed.getValue().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
            Stage stage = (Stage) editMovieCreate.getScene().getWindow();
            stage.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Handles EditMovieCancel action connected to the movie.
     * @param actionEvent
     */
    public void handleEditMovieCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) editMovieCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the process of editing a movie within the GUI.
     * It sets the values of the selected movie by calling getters from Controller.java
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            movieModel = new MovieModel();
            Controller controller = new Controller();
            localGenreList = controller.getGenreList();
            editMovieGenre.setItems(localGenreList);
            editMovieName.setText(Controller.getEditMovieName());
            editMovieImdbRating.setText(Controller.getEditMovieImdb().toString());
            editMoviePersonalRating.setText(Controller.getEditMovieRating().toString());
            lastViewed.setValue(Controller.getEditMovieLastWatched());
            editMovieFilepath.setText(Controller.getEditMovieFilePath());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the LastViewed action connected to the GUI.
     * @param actionEvent
     */
    public void handleLastViewed(ActionEvent actionEvent) {
    }

    /**
     * Handles the EditMovieGenre action connected to the GUI.
     * @param actionEvent
     */
    public void handleEditMovieGenre(ActionEvent actionEvent) {

    }

}
