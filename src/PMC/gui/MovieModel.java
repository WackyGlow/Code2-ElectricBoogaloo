package PMC.gui;

import PMC.be.Movie;
import PMC.bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class MovieModel {

    private MovieManager movieManager;
    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();


    public MovieModel() throws IOException {
        movieManager = new MovieManager();
    }

    public ObservableList<Movie> getAllMovies() throws IOException {
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
        return allMovies;
    }

    public void createMovie(String name, int imdb, int rating, String filepath, String lastwatched) throws SQLException {
        movieManager.createMovie(name,imdb,rating,filepath,lastwatched);
    }

}
