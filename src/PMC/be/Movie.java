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

    /**
     * Constructor for the movie class.
     * @param name
     * @param imdbRating
     * @param rating
     * @param filePath
     * @param lastWatched
     * @param id
     */
    public Movie(String name, int imdbRating, int rating, String filePath, String lastWatched, int id){
    this.name = new SimpleStringProperty(name);
    this.filePath = filePath;
    this.id = id;
    this.imdbRating = new SimpleIntegerProperty(imdbRating).asObject();
    this.rating = new SimpleIntegerProperty(rating).asObject();;
    this.lastWatched = new SimpleStringProperty(lastWatched);
}

    /**
     * Returns the name of a movie.
     * @return
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of a movie.
     * @param name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Returns the name as an observable value, for GUI purposes.
     * @return
     */
    public ObservableValue<String> nameProperty() {
        return name;
    }

    /**
     * Returns the imdbRating as an object property, for GUI purposes.
     * @return
     */
    public ObjectProperty<Integer> getImdbRating() {
        return imdbRating;
    }

    /**
     * Sets the imdbRating of a movie.
     * @param imdbRating
     */
    public void setImdbRating(int imdbRating) {
        this.imdbRating.set(imdbRating);
    }

    /**
     * Returns the imdbRating as an observable value, for GUI purposes.
     * @return
     */
    public ObservableValue<Integer> imdbRatingProperty() {
        return imdbRating;
    }

    /**
     * Returns the user rating as an object property, for GUI purposes.
     * @return
     */
    public ObjectProperty<Integer> getRating() {
        return rating;
    }

    /**
     * Sets the user rating of a movie.
     * @param rating
     */
    public void setRating(int rating) {
        this.rating.set(rating);
    }

    /**
     * Returns the user rating as an observable value, for GUI purposes.
     * @return
     */
    public ObservableValue<Integer> ratingProperty() {
        return rating;
    }

    /**
     * Return the filepath of a movie.
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the filepath of a movie.
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the lastWatched of a movie.
     * @return
     */
    public String getLastWatched() {
        return lastWatched.get();
    }

    /**
     * Sets the lastWatched of a movie.
     * @param lastWatched
     */
    public void setLastWatched(String lastWatched) {
        this.lastWatched.set(lastWatched);
    }

    /**
     * Returns the lastWatched as an observable value, for GUI purposes.
     * @return
     */
    public ObservableValue<String> lastWatchedProperty() {
        return lastWatched;
    }

    /**
     * Returns the id of a movie.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of a movie.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

}
