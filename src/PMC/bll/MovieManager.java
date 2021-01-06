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

}
