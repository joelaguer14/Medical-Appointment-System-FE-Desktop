package cr.una.proyecto.frontend.model;

public class Address {
    private int id;
    private String address;
    private String type;

    public Address() {
    }

    public Address(int id, String address, String type) {
        this.id = id;
        this.address = address;
        this.type = type;
    }

    public Address(String address, String type) {
        this.address = address;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
