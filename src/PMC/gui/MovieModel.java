package PMC.gui;

import PMC.bll.MovieManager;

import java.sql.SQLException;

public class MovieModel {

    private MovieManager movieManager;

    public MovieModel() {
        movieManager = new MovieManager();
    }

    public void createMovie(String name, float imdb, float rating, String filepath, String lastwatched) throws SQLException {
        movieManager.createMovie(name,imdb,rating,filepath,lastwatched);
    }

}
