package PMC.be;

public class Genre {
    private int id;
    private String name;

public Genre(int id, String name){
    this.id = id;
    this.name = name;
}

public String getName(){return name;}

public int getId(){return id;}

private void setName(String name){this.name = name;}

}
