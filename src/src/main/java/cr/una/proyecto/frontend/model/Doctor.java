package cr.una.proyecto.frontend.model;

public class Doctor {
    private int id;

    private String name;

    private boolean premium;

    private String medicalCode;

    /**
     *
     */
    public Doctor() {
    }

    /**
     * @param id
     * @param name
     * @param premium
     * @param doctorType
     */
    public Doctor(int id, String name, boolean premium, DoctorType doctorType, String medicalCode) {
        this.id = id;
        this.name = name;
        this.premium = premium;
        this.medicalCode = medicalCode;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public boolean getPremium() {
        return premium;
    }

    /**
     * @return
     */
    public String getMedicalCode() {
        return medicalCode;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param premium
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
     * @param medicalCode
     */
    public void setMedicalCode(String medicalCode) {
        this.medicalCode = medicalCode;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", premium=" + premium +
                ", medicalCode='" + medicalCode + '\'' +
                '}';
    }
}
