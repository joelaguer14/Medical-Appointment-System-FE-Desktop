package cr.una.proyecto.frontend.model;

import java.util.List;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class DoctorOffice {

    private int id;
    private String name;
    private String days;
    private String hour;
    private List<Address> addresses;
    private List<Phone> phones;

    /**
     * Default constructor
     */
    public DoctorOffice() {
    }

    /**
     * Constructor with parameters.
     *
     * @param name      name of the consulting room
     * @param days      days it is available
     * @param hour      hours when it is available
     * @param addresses addresses of the Doctor Office
     * @param phones    phone numbers of the Doctor Office
     */
    public DoctorOffice(String name, String days, String hour, List<Address> addresses, List<Phone> phones) {
        this.name = name;
        this.days = days;
        this.hour = hour;
        this.addresses = addresses;
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDays() {
        return days;
    }

    public String getHour() {
        return hour;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "DoctorOffice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", days='" + days + '\'' +
                ", hour='" + hour + '\'' +
                ", addresses=" + addresses +
                ", phones=" + phones +
                '}';
    }
}
