package cr.una.proyecto.frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 * <p>
 * Implementation of Id class
 */
/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 *
 * Implementation of Id class
 */
public class Id {

    @JsonProperty("$oid")
    private String $oid;

    /**
     * Default constructor
     */
    public Id() {
    }

    /**
     * Constructor with parameters
     *
     * @param $oid, $oid
     */
    public Id(String $oid) {
        this.$oid = $oid;
    }

    /**
     *  Returns $oid
     *
     * @return $oid
     */
    public String get$oid() {
        return $oid;
    }

    /**
     * Set $oid
     *
     * @param $oid, $oid
     */
    public void set$oid(String $oid) {
        this.$oid = $oid;
    }

    /**
     * Show the attributes of the class
     *
     * @return a string
     */
    @Override
    public String toString() {
        return "Id{" + "$oid=" + $oid + '}';
    }
}