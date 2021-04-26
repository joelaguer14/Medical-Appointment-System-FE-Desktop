package cr.una.proyecto.frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 *
 * Implementation of DoctorType class
 */
public class DoctorType {
    @JsonProperty("_id")
    private Id id;
    @JsonProperty("tipo")
    private String type;
    @JsonProperty("salario")
    private String salary;
    @JsonProperty("moneda")
    private String coin;
    @JsonProperty("codigo-medico")
    private String medicalCode;

    /**
     * Default constructor
     */
    public DoctorType() {
    }

    /**
     * Constructor with parameters
     *
     * @param id, id code of the doctor type
     * @param type, type code of the doctor type
     * @param salary, salary code of the doctor type
     * @param coin, coin code of the doctor type
     * @param medicalCode, medical code of the doctor type
     */
    public DoctorType(Id id, String type, String salary, String coin, String medicalCode) {
        this.id = id;
        this.type = type;
        this.salary = salary;
        this.coin = coin;
        this.medicalCode = medicalCode;
    }

    public Id getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSalary() {
        return salary;
    }

    public String getCoin() {
        return coin;
    }

    public String getMedicalCode() {
        return medicalCode;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public void setMedicalCode(String medicalCode) {
        this.medicalCode = medicalCode;
    }

    @Override
    public String toString() {
        return "DoctorType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", salary='" + salary + '\'' +
                ", coin='" + coin + '\'' +
                ", medicalCode='" + medicalCode + '\'' +
                '}';
    }
}
