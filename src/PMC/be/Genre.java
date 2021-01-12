package PMC.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Genre {
    private int id;
    private StringProperty name;

    /**
     * Constructor for the genre class.
     * @param id
     * @param name
     */
    public Genre(int id, String name){
        this.id = id;
        this.name = new SimpleStringProperty(name);
    }

    /**
     * Returns the name of a genre.
     * @return
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of a genre.
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
     * Returns the id of a genre.
     * @return
     */
    public int getId(){
        return id;
    }



}
