package cr.una.proyecto.frontend.model;

public class Phone {
    private int id;
    private String phone;
    private String phoneType;

    /**
     *
     */
    public Phone() {
    }

    public Phone(String phone, String phoneType) {
        this.phone = phone;
        this.phoneType = phoneType;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", phoneType='" + phoneType + '\'' +
                '}';
    }
}
