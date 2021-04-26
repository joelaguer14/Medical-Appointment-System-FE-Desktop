package cr.una.proyecto.frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceType {
    @JsonProperty("_id")
    private Id id;
    @JsonProperty("servicio")
    private String service;
    @JsonProperty("costo-porcentual")
    private String percentageCost;
    @JsonProperty("codigo-medico")
    private String medicalCode;
    @JsonProperty("costo")
    private String cost;
    @JsonProperty("moneda")
    private String coin;

    public ServiceType() {
    }

    public ServiceType(Id id, String service, String percentageCost, String medicalCode, String cost, String coin) {
        this.id = id;
        this.service = service;
        this.percentageCost = percentageCost;
        this.medicalCode = medicalCode;
        this.cost = cost;
        this.coin = coin;
    }

    public Id getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public String getPercentageCost() {
        return percentageCost;
    }

    public String getMedicalCode() {
        return medicalCode;
    }

    public String getCost() {
        return cost;
    }

    public String getCoin() {
        return coin;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setPercentageCost(String percentageCost) {
        this.percentageCost = percentageCost;
    }

    public void setMedicalCode(String medicalCode) {
        this.medicalCode = medicalCode;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", service='" + service + '\'' +
                ", percentageCost='" + percentageCost + '\'' +
                ", medicalCode='" + medicalCode + '\'' +
                ", cost='" + cost + '\'' +
                ", coin='" + coin + '\'' +
                '}';
    }
}
