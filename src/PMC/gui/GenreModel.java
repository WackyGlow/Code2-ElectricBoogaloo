package PMC.gui;

import PMC.be.Genre;
import PMC.bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class GenreModel {
    private MovieManager movieManager;
    private ObservableList<Genre> allgenres = FXCollections.observableArrayList();


    public GenreModel() throws IOException {
        movieManager = new MovieManager();
    }

    public ObservableList<Genre> getAllMovies() throws IOException {
        allgenres = FXCollections.observableArrayList();
        allgenres.addAll(movieManager.getAllGenres());
        return allgenres;
    }


    public void createGenre(String name) throws SQLException {
        movieManager.createGenre(name);
    }
}
