package cr.una.proyecto.frontend.model;

public class Disease {
    private int id;
    private String Disease;

    public Disease() {
    }

    public Disease(int id, String disease) {
        this.id = id;
        Disease = disease;
    }

    public Disease(String disease) {
        Disease = disease;
    }

    public int getId() {
        return id;
    }

    public String getDisease() {
        return Disease;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", Disease='" + Disease + '\'' +
                '}';
    }
}
