package PMC.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
    private StringProperty name;
    private int imdbRating;
    private int rating;
    private String filePath;
    private StringProperty lastWatched;
    private int id;

public Movie(String name, int imdbRating, int rating, String filePath, String lastWatched, int id){
    this.name = new SimpleStringProperty(name);
    this.filePath = filePath;
    this.id = id;
    this.imdbRating = imdbRating;
    this.rating = rating;
    this.lastWatched = new SimpleStringProperty(lastWatched);
}

    public String getName() {
        return name.get();
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public float getRating() {
        return rating;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getLastWatched() {
        return lastWatched.get();
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setImdbRating(int imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setLastWatched(String lastWatched) {
        this.lastWatched.set(lastWatched);
    }

    public void setId(int id) {
        this.id = id;
    }
}
