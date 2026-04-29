package datosmedicos_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

// Entidad que representa medicamentos asociados a una ficha clinica
@Entity
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

    private String nombre;
    private String dosis;
    private String frecuencia;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    @JsonIgnore
    private FichaClinica ficha;

    public Medicamento() {
    }

    public Medicamento(String nombre, String dosis, String frecuencia, FichaClinica ficha) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.ficha = ficha;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public FichaClinica getFicha() {
        return ficha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setFicha(FichaClinica ficha) {
        this.ficha = ficha;
    }
}