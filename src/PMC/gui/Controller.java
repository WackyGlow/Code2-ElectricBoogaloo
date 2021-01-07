package PMC.gui;

import PMC.be.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TableView genreList;
    @FXML
    public TableColumn genreNameColumn;
    @FXML
    public TableColumn genreMoviesColumn;
    @FXML
    public TableView<Movie> movieList;
    @FXML
    public TableColumn<String, Movie> movieNameColumn;
    @FXML
    public TableColumn<Float,Movie> movieImdbColumn;
    @FXML
    public TableColumn<Float,Movie> movieRatingColumn;
    @FXML
    public TableColumn<String,Movie> lastViewedColumn;
    @FXML
    public Button createGenre;
    @FXML
    public Button deleteGenre;
    @FXML
    public Button editGenre;
    @FXML
    public Button createMovie;
    @FXML
    public Button deleteMovie;
    @FXML
    public Button editMovie;
    private ObservableList observableListMovies;
    private MovieModel movieModel;
    private Movie selectedMovie;

    public Controller() throws IOException {
        movieModel = new MovieModel();
        movieList = new TableView<>();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void handleCreateGenre(ActionEvent actionEvent) {
        try {
            Parent genre = FXMLLoader.load(getClass().getResource("NewGenreView.fxml"));
            Scene scene = new Scene(genre);
            Stage stage = new Stage();
            stage.setTitle("Create New Genre");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleDeleteGenre(ActionEvent actionEvent) {

    }

    public void handleEditGenre(ActionEvent actionEvent) {

    }

    public void handleCreateMovie(ActionEvent actionEvent)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("NewMovieView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Create New Movie");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteMovie(ActionEvent actionEvent) {
    }

    public void handleEditMovie(ActionEvent actionEvent) {
    }


}
