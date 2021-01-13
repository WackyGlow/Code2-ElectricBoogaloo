package PMC.gui;

import PMC.be.Movie;
import PMC.bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MovieModel {

    private MovieManager movieManager;
    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();

    /**
     * Implements the MovieManager class.
     * @throws IOException
     */
    public MovieModel() throws IOException {
        movieManager = new MovieManager();
    }

    /**
     * Returns the getAllMovies observable arraylist.
     * @return
     * @throws IOException
     */
    public ObservableList<Movie> getAllMovies() throws IOException {
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
        return allMovies;
    }

    /**
     * Handles the create movie action.
     * @param name
     * @param imdb
     * @param rating
     * @param filepath
     * @param lastwatched
     * @throws SQLException
     */
    public void createMovie(String name, int imdb, int rating, String filepath, String lastwatched) throws SQLException {
        movieManager.createMovie(name,imdb,rating,filepath,lastwatched);
    }

    public void updateMovie(Movie movie, String name, int imdb, int rating, String filepath, String lastwatched) throws SQLException {
        movieManager.updateMovie(movie,name,imdb,rating,filepath,lastwatched);
    }
}
