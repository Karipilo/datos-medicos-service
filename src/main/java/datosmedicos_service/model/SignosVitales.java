package datosmedicos_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "signos_vitales")
@Data
@AllArgsConstructor

public class SignosVitales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String presion;

    private Integer frecuencia;

    private Double temperatura;

    private Integer saturacion;

    private String profesional;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    private FichaClinica ficha;

    public SignosVitales() {
    }

    public SignosVitales(
            String presion,
            Integer frecuencia,
            Double temperatura,
            Integer saturacion,
            String profesional,
            LocalDateTime fecha) {

        this.presion = presion;
        this.frecuencia = frecuencia;
        this.temperatura = temperatura;
        this.saturacion = saturacion;
        this.profesional = profesional;
        this.fechaRegistro = fecha;
    }

    public Long getId() {
        return id;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(Integer saturacion) {
        this.saturacion = saturacion;
    }

    public String getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public FichaClinica getFicha() {
        return ficha;
    }

    public void setFicha(FichaClinica ficha) {
        this.ficha = ficha;
    }
}