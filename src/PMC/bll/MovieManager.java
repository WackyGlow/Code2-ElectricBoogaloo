package PMC.bll;

import PMC.be.Genre;
import PMC.be.Movie;
import PMC.dal.GenreDAO;
import PMC.dal.GenreMovieDAO;
import PMC.dal.MovieDAO;
import PMC.gui.EditMovieViewController;
import PMC.gui.NewMovieViewController;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieManager {
    private MovieDAO movieDAO;
    private GenreDAO genreDAO;
    private GenreMovieDAO genreMovieDAO;

    /**
     * Implements the genreDAO and the movieDAO classes.
     * @throws IOException
     */
    public MovieManager() throws IOException {
        movieDAO = new MovieDAO();
        genreDAO = new GenreDAO();
        genreMovieDAO = new GenreMovieDAO();
}

    /**
     * Makes the application play the selected movie.
     * @param path
     * @throws IOException
     */
    public void playMovie(String path) throws IOException {
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
     * Makes it possible to update the selected movie.
     * @param movie
     * @param title
     * @param imdb
     * @param rating
     * @param filepath
     * @param lastwatched
     * @throws SQLException
     */

    public void updateMovie(Movie movie,String title, int imdb, int rating, String filepath, String lastwatched) throws SQLException {
        movieDAO.updateMovie(movie, title,imdb,rating, filepath, lastwatched);
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

    /**
     * Returns the list of movies from a given genre.
     * @param genre
     * @return
     * @throws IOException
     */
    public List<Movie> getAllMoviesFromGenre(Genre genre) throws IOException {
        return genreMovieDAO.getAllMoviesFromGenre(genre);
    }

    /**
     * this method uses a for loop to remove movies with a rating under the given number from a list of movies
     * @param listOfMovies
     * @param searchedRating
     * @return
     */
    public ObservableList<Movie> searchIMDBRating (ObservableList<Movie> listOfMovies,int searchedRating) {
        List<Movie> toRemove = new ArrayList();
        for (Movie movie:listOfMovies) {
            ObjectProperty<Integer> MoviesRatingOP = movie.getImdbRating();
            int MoviesRating = MoviesRatingOP.getValue();
            if( MoviesRating <= searchedRating -1) {
                toRemove.add(movie);
            }
        }
        listOfMovies.removeAll(toRemove);
        toRemove.clear();
        return listOfMovies;
    }

    /**
     * This method uses a for loop to remove movies whose title dose not contain the searched keyword
     * @param listOfMovies
     * @param searchedTitle
     * @return
     */
    public ObservableList<Movie> searchTitle (ObservableList<Movie> listOfMovies,String searchedTitle) {
        List<Movie> toRemove = new ArrayList();
        for (Movie movie:listOfMovies) {
            String TitleOfMovie = movie.getName().toLowerCase();
            boolean Found = TitleOfMovie.contains(searchedTitle.toLowerCase());
            if( Found == false) {
                toRemove.add(movie);
            }
        }
        listOfMovies.removeAll(toRemove);
        toRemove.clear();
        return listOfMovies;
    }

    /**
     * Makes is possible to edit a selected genre.
     * @param genre
     * @param newname
     * @throws SQLException
     */
    public void editGenre(Genre genre, String newname) throws SQLException {
        genreDAO.editGenre(genre, newname);
    }


    public List<Genre> getNewMovieSelectedGenres(){
        return NewMovieViewController.getNewMovieSelectedGenres();
    }
    public List<Genre> getEditMovieSelectedGenres(){
        return EditMovieViewController.getEditMovieSelectedGenres();
    }
}
