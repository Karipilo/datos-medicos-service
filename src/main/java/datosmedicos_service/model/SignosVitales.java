package datosmedicos_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "signos_vitales")
public class SignosVitales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String presion;

    private Integer frecuencia;

    private Double temperatura;

    private Integer saturacion;

    private String profesional;

    private String fecha;

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
            String fecha
    ) {

        this.presion = presion;
        this.frecuencia = frecuencia;
        this.temperatura = temperatura;
        this.saturacion = saturacion;
        this.profesional = profesional;
        this.fecha = fecha;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public FichaClinica getFicha() {
        return ficha;
    }

    public void setFicha(FichaClinica ficha) {
        this.ficha = ficha;
    }
}