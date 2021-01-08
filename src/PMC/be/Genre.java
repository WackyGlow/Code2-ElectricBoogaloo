package PMC.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Genre {
    private int id;
    private StringProperty name;

    public Genre(int id, String name){
        this.id = id;
        this.name = new SimpleStringProperty(name);
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

    public int getId(){
        return id;
    }



}
