package PMC.gui;

import PMC.be.Genre;
import PMC.be.Movie;
import PMC.bll.MovieManager;
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
import java.sql.SQLException;
import java.util.EventObject;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TableView<Genre> genreList;
    @FXML
    public TableColumn<Genre, String> genreNameColumn;
    @FXML
    public TableColumn<Genre, Integer> genreMoviesColumn;


    @FXML
    public TableView<Movie> movieList;
    @FXML
    public TableColumn<Movie, String> movieNameColumn;
    @FXML
    public TableColumn<Movie, Integer> movieImdbColumn;
    @FXML
    public TableColumn<Movie, Integer> movieRatingColumn;
    @FXML
    public TableColumn<Movie, String> lastViewedColumn;


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
    @FXML
    public Button refreshTables;

    private MovieManager movieManager;
    private ObservableList<Movie> movies;
    private ObservableList<Genre> genres;
    private MovieModel movieModel;
    private GenreModel genreModel;
    private Movie selectedMovie;
    private Genre selectedGenre;

    public Controller() throws IOException {
        movieModel = new MovieModel();
        genreModel = new GenreModel();
        movieManager = new MovieManager();
        movieList = new TableView<>();
        genreList = new TableView<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            initMovieTable();
            initGenreTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initMovieTable() throws IOException {
        movies = movieModel.getAllMovies();
        if (movies == null)
            throw new RuntimeException("Error retrieving movies from Database");
        movieNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        movieImdbColumn.setCellValueFactory(cellData -> cellData.getValue().imdbRatingProperty());
        movieRatingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        lastViewedColumn.setCellValueFactory(cellData -> cellData.getValue().lastWatchedProperty());

        movieList.setItems(movies);
    }

    public void initGenreTable() throws IOException {
        genres = genreModel.getAllGenres();
        if (genres == null)
            throw new RuntimeException("Error retrieving genres from Database");
        genreNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        genreList.setItems(genres);

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
        selectedGenre = genreList.getSelectionModel().getSelectedItem();
        if(selectedGenre != null) {
            try {
                movieManager.deleteGenre(selectedGenre);
                genres.remove(selectedGenre);
                genreList.getItems().clear();
                genreList.getItems().addAll(genres);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
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

    public void handleDeleteMovie(ActionEvent actionEvent) throws SQLException {
        selectedMovie = movieList.getSelectionModel().getSelectedItem();
        if(selectedMovie != null) {
            try {
                movieManager.deleteMovie(selectedMovie);
                movies.remove(selectedMovie);
                movieList.getItems().clear();
                movieList.getItems().addAll(movies);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void handleEditMovie(ActionEvent actionEvent) throws IOException {

    }

    public void handleRefreshTables(ActionEvent actionEvent) throws IOException {

        //Refreshes the movie list.
        movies = movieModel.getAllMovies();
        movieList.getItems().clear();
        movieList.getItems().addAll(movies);

        //Refreshes the genre list.
        genres = genreModel.getAllGenres();
        genreList.getItems().clear();
        genreList.getItems().addAll(genres);
    }

}
