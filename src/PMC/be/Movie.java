package PMC.be;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class Movie {
    private StringProperty name;
    private ObjectProperty<Integer> imdbRating;
    private ObjectProperty<Integer> rating;
    private String filePath;
    private StringProperty lastWatched;
    private int id;

public Movie(String name, int imdbRating, int rating, String filePath, String lastWatched, int id){
    this.name = new SimpleStringProperty(name);
    this.filePath = filePath;
    this.id = id;
    this.imdbRating = new SimpleIntegerProperty(imdbRating).asObject();
    this.rating = new SimpleIntegerProperty(rating).asObject();;
    this.lastWatched = new SimpleStringProperty(lastWatched);
}

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public ObservableValue<String> nameProperty() {
        return name;
    }


    public ObjectProperty<Integer> getImdbRating() {
        return imdbRating;
    }
    public void setImdbRating(int imdbRating) {
        this.imdbRating.set(imdbRating);
    }
    public ObservableValue<Integer> imdbRatingProperty() {
        return imdbRating;
    }


    public ObjectProperty<Integer> getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating.set(rating);
    }
    public ObservableValue<Integer> ratingProperty() {
        return rating;
    }


    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLastWatched() {
        return lastWatched.get();
    }
    public void setLastWatched(String lastWatched) {
        this.lastWatched.set(lastWatched);
    }
    public ObservableValue<String> lastWatchedProperty() {
        return lastWatched;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }






}
