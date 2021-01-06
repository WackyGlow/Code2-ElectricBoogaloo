package PMC.bll;

import PMC.be.Movie;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MovieManager {

public MovieManager() {
}

public void playMovie(String path) throws IOException {
    File film = new File(path);
    Desktop desktop = Desktop.getDesktop();
    desktop.open(film);
}

public void editName(Movie movie, String name){
    movie.setName(name);
}
public void editImdbrating(Movie movie, float rating){
    movie.setImdbRating(rating);
}
public void editRating(Movie movie, float rating){
    movie.setRating(rating);
}
public void editFilePath(Movie movie, String path){
    movie.setFilePath(path);
}
}
