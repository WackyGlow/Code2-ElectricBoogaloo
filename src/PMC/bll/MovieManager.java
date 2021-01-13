package PMC.bll;

import PMC.be.Genre;
import PMC.be.Movie;
import PMC.dal.GenreDAO;
import PMC.dal.MovieDAO;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MovieManager {
    private MovieDAO movieDAO;
    private GenreDAO genreDAO;

    /**
     * Implements the genreDAO and the movieDAO classes.
     * @throws IOException
     */
    public MovieManager() throws IOException {
        movieDAO = new MovieDAO();
        genreDAO = new GenreDAO();
}

    /**
     * Makes the application play the selected movie.
     * @param movie
     * @throws IOException
     */
    public void playMovie(Movie movie) throws IOException {
        String path = movie.getFilePath();
        File film = new File(path);
        Desktop desktop = Desktop.getDesktop();
        desktop.open(film);
    }

    /**
     * Makes it possible to edit the name of a movie.
     * @param movie
     * @param name
     */
    public void editName(Movie movie, String name){
    movie.setName(name);
}

    /**
     * Makes it possible to edit the Imdb rating of a movie.
     * @param movie
     * @param rating
     */
    public void editImdbrating(Movie movie, int rating){
    movie.setImdbRating(rating);
}

    /**
     * Makes it possible to edit the user rating of a movie.
     * @param movie
     * @param rating
     */
    public void editRating(Movie movie, int rating){
    movie.setRating(rating);
}

    /**
     * Makes it possible to edit the file path of a movie.
     * @param movie
     * @param path
     */
    public void editFilePath(Movie movie, String path){
    movie.setFilePath(path);
}

    /**
     * Makes it possible to create a new movie.
     * @param title
     * @param imdb
     * @param rating
     * @param filepath
     * @param lastwatched
     * @return
     * @throws SQLException
     */
    public Movie createMovie(String title, int imdb, int rating, String filepath, String lastwatched) throws SQLException {
        return movieDAO.createMovie(title,imdb,rating,filepath,lastwatched);
    }

    /**
     * Makes it possible to delete the selected movie.
     * @param selectedMovie
     * @throws SQLException
     */
    public void deleteMovie(Movie selectedMovie) throws SQLException {
        movieDAO.deleteMovie(selectedMovie);
    }

    /**
     * Makes it possible ot create a new genre.
     * @param name
     * @return
     * @throws SQLException
     */
    public Genre createGenre(String name) throws SQLException {
        return genreDAO.createGenre(name);
    }

    /**
     * Makes it possible to delete the selected genre.
     * @param selectedGenre
     * @throws SQLException
     */
    public void deleteGenre(Genre selectedGenre) throws SQLException {
        genreDAO.deleteGenre(selectedGenre);
    }

    /**
     * Returns the list of movies, created in the movieDAO.
     * @return
     * @throws IOException
     */
    public List<Movie> getAllMovies() throws IOException {
        return movieDAO.getAllMovies();
    }

    /**
     * Returns the list of genres, created in the genreDAO
     * @return
     * @throws IOException
     */
    public List<Genre> getAllGenres() throws IOException {
        return genreDAO.getAllGenres();
    }

    public void editMovie(Movie selectedMovie, String title, int rating, int userrating, String filepath, String lastview) throws SQLException {
        movieDAO.updateMovie(selectedMovie,title,rating,userrating,filepath,lastview);
    }
}
