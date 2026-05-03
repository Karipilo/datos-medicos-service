package datosmedicos_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "indicaciones_medicas")
public class IndicacionMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;

    private String profesional;

    @Column(length = 2000)
    private String indicacion;

    public IndicacionMedica() {
    }

    public IndicacionMedica(
            String fecha,
            String profesional,
            String indicacion
    ) {
        this.fecha = fecha;
        this.profesional = profesional;
        this.indicacion = indicacion;
    }

    public Long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getProfesional() {
        return profesional;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }
}