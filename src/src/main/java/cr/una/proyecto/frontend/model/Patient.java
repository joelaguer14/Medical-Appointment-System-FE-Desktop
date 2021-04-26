package cr.una.proyecto.frontend.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class Patient {
    private int id;
    private String fullName;
    private List<Phone> phones;
    private List<Address> addresses;
    private List<Disease> diseases;
    private String observations;
    private Date birthDate;

    public Patient() {

    }

    /**
     * Constructor with parameters.
     *
     * @param fullName     patient's full name
     * @param observations patient's observations
     * @param birthDate    patient's date of birth
     */
    public Patient(String fullName, String observations, Date birthDate) {
        this.fullName = fullName;
        this.addresses = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.diseases = new ArrayList<>();
        this.observations = observations;
        this.birthDate = birthDate;
    }

    public Patient(int id, String fullName, List<Phone> phones, List<Address> addresses, List<Disease> diseases, String observations, Date birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.phones = phones;
        this.addresses = addresses;
        this.diseases = diseases;
        this.observations = observations;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public String getObservations() {
        return observations;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phones=" + phones +
                ", addresses=" + addresses +
                ", diseases=" + diseases +
                ", observations='" + observations + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}