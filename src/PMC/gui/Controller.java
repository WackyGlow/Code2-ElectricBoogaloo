package PMC.gui;

import PMC.be.Genre;
import PMC.be.Movie;
import PMC.bll.DateManager;
import PMC.bll.MovieManager;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TableView<Genre> genreList;
    @FXML
    public TableColumn<Genre, String> genreNameColumn;

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
    @FXML
    public TextField filterMovie;
    @FXML
    public Button playMovieButton;

    private MovieManager movieManager;
    private ObservableList<Movie> movies;
    private ObservableList<Genre> genres;
    private MovieModel movieModel;
    private GenreModel genreModel;
    private Movie selectedMovie;
    private Genre selectedGenre;

    private static String editMovieName;
    private static ObjectProperty<Integer> editMovieImdb;
    private static ObjectProperty<Integer> editMovieRating;
    private static LocalDate editMovieLastWatched;
    private static String editMovieFilePath;

    /**
     * Implements the classes listed.
     * @throws IOException
     */
    public Controller() throws IOException {
        movieModel = new MovieModel();
        genreModel = new GenreModel();
        movieManager = new MovieManager();
        movieList = new TableView<>();
        genreList = new TableView<>();
    }

    /**
     * Calls both the the initialize methods for the respectable tables.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            initMovieTable();
            initGenreTable();
            if(compareDate() == true) {

            }
        } catch (IOException | ParseException e) {

        }
    }

    /**
     * Initializes the movie table.
     * @throws IOException
     */
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

    /**
     * Initializes the genre table.
     * @throws IOException
     */
    public void initGenreTable() throws IOException {
        genres = genreModel.getAllGenres();
        if (genres == null)
            throw new RuntimeException("Error retrieving genres from Database");
        genreNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        genreList.setItems(genres);

    }

    /**
     * Handles the create genre action.
     * @param actionEvent
     */
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

    /**
     * Handles the delete genre action.
     * @param actionEvent
     */
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

    /**
     * Handles the edit genre action.
     * @param actionEvent
     */
    public void handleEditGenre(ActionEvent actionEvent) {

    }


    /**
     * Handles the create Movie action.
     * @param actionEvent
     */
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

    /**
     * Handles the delete movie action.
     * @param actionEvent
     */
    public void handleDeleteMovie(ActionEvent actionEvent) {
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

    /**
     * Handles the edit movie action.
     * @param actionEvent
     * @throws IOException
     */
    public void handleEditMovie(ActionEvent actionEvent) throws IOException {
        selectedMovie = movieList.getSelectionModel().getSelectedItem();
        editMovieName = selectedMovie.getName();
        editMovieImdb = selectedMovie.getImdbRating();
        editMovieRating = selectedMovie.getRating();

        // Converts the LastWatched String back to a LocalDate to display in edit window.
        String date = selectedMovie.getLastWatched();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        editMovieLastWatched = LocalDate.parse(date, formatter);

        editMovieFilePath = selectedMovie.getFilePath();

        if (selectedMovie != null){
            Parent root = FXMLLoader.load(getClass().getResource("EditMovieView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Edit Movie");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Gets the name of the selected movie. This is how to call it from the edit window.
     * @return editMovieName
     */
    public static String getEditMovieName() {
        return editMovieName;
    }
    public static Integer getEditMovieImdb() {
        return editMovieImdb.get();
    }
    public static Integer getEditMovieRating() {
        return editMovieRating.get();
    }
    public static LocalDate getEditMovieLastWatched() {
        return editMovieLastWatched;
    }
    public static String getEditMovieFilePath() {
        return editMovieFilePath;
    }

    /**
     * Handles the refresh tables action.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Getter for the observable list of movies
     * @return movies
     */
    public ObservableList<Movie> getMovieList(){
        return movies;
    }

    /**
     * Getter for the observable list of genres
     * @return genres
     */
    public ObservableList<Genre> getGenreList() throws IOException {
        genres = genreModel.getAllGenres();
        return genres;
    }

    /**
     * Handles the even that occurs when typing text into the FilterTextList.
     * @param actionEvent
     */
    public void handleFilterMovie(ActionEvent actionEvent){
    }

    /**
     * Handles the event that occurs when pressing the play button.
     * @param actionEvent
     */
    public void handlePlayMovieButton(ActionEvent actionEvent) throws IOException {
        selectedMovie = movieList.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            movieManager.playMovie(selectedMovie.getFilePath());
        }
    }

    /**
     * this method is used to check if any movies were last watched over six months ago
     * @return
     * @throws ParseException
     */
    public boolean compareDate() throws ParseException {
        DateManager DM = new DateManager();
        for (Movie movie:movies) {
            DM.checkDate(movie);
        }
        if(DM.getMoviesOverSixMonths() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
