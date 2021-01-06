package PMC.be;

import java.io.File;

public class Movie {
    private String name;
    private float imdbRating;
    private float rating;
    private String filePath;
    private String lastWatched;
    private int id;

public Movie(String name,float imdbRating,float rating,String filePath,String lastWatched){

}

    public String getName() {
        return name;
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
        return lastWatched;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setLastWatched(String lastWatched) {
        this.lastWatched = lastWatched;
    }

    public void setId(int id) {
        this.id = id;
    }
}
