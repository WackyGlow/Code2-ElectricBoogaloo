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

    /**
     * Implements the MovieManager class.
     * @throws IOException
     */
    public GenreModel() throws IOException {
        movieManager = new MovieManager();
    }

    /**
     * Returns the allGenres observable arraylist
     * @return
     * @throws IOException
     */
    public ObservableList<Genre> getAllGenres() throws IOException {
        allGenres = FXCollections.observableArrayList();
        allGenres.addAll(movieManager.getAllGenres());
        return allGenres;
    }

    /**
     * Takes a new genre name and parses it to the MovieManager.
     * @param name
     * @throws SQLException
     */
    public void createGenre(String name) throws SQLException {
        movieManager.createGenre(name);
    }

    /**
     * Takes selected genre and parses it to the MovieManager.
     * @param genre
     * @param newname
     * @throws SQLException
     */
    public void updateGenre(Genre genre, String newname) throws SQLException {
        movieManager.editGenre(genre, newname);
    }

}
