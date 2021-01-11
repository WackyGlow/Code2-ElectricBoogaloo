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

    public MovieManager() throws IOException {
        movieDAO = new MovieDAO();
        genreDAO = new GenreDAO();
}

    public void playMovie(String path) throws IOException {
        File film = new File(path);
        Desktop desktop = Desktop.getDesktop();
        desktop.open(film);
    }

    public void editName(Movie movie, String name){
    movie.setName(name);
}
    public void editImdbrating(Movie movie, int rating){
    movie.setImdbRating(rating);
}
    public void editRating(Movie movie, int rating){
    movie.setRating(rating);
}
    public void editFilePath(Movie movie, String path){
    movie.setFilePath(path);
}

    public Movie createMovie(String title, int imdb, int rating, String filepath, String lastwatched) throws SQLException {
        return movieDAO.createMovie(title,imdb,rating,filepath,lastwatched);
    }
    public void deleteMovie(Movie selectedMovie) throws SQLException {
        movieDAO.deleteMovie(selectedMovie);
    }

    public Genre createGenre(String name) throws SQLException {
        return genreDAO.createGenre(name);
    }
    public void deleteGenre(Genre selectedGenre) throws SQLException {
        genreDAO.deleteGenre(selectedGenre);
    }

    public List<Movie> getAllMovies() throws IOException {
        return movieDAO.getAllMovies();
    }

    public List<Genre> getAllGenres() throws IOException {
        return genreDAO.getAllGenres();
    }


}
