package PMC.gui;

import PMC.be.Genre;
import PMC.bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class GenreModel {
    private MovieManager movieManager;
    private ObservableList<Genre> allGenres = FXCollections.observableArrayList();


    public GenreModel() throws IOException {
        movieManager = new MovieManager();
    }

    public ObservableList<Genre> getAllGenres() throws IOException {
        allGenres = FXCollections.observableArrayList();
        allGenres.addAll(movieManager.getAllGenres());
        return allGenres;
    }


    public void createGenre(String name) throws SQLException {
        movieManager.createGenre(name);
    }
}
