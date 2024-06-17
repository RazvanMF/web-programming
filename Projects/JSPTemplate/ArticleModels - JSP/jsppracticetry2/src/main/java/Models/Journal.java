package Models;

public class Journal {
    public int id;
    public String name;

    public Journal(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Journal() {
        this.id = 0;
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
