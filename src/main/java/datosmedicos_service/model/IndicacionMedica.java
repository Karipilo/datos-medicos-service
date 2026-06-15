package datosmedicos_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "indicaciones_medicas")
public class IndicacionMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    private String profesional;

    @Column(length = 2000)
    private String indicacion;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    private FichaClinica ficha;

    public IndicacionMedica() {
    }

    public IndicacionMedica(
            LocalDateTime fechaRegistro,
            String profesional,
            String indicacion) {

        this.fechaRegistro = fechaRegistro;
        this.profesional = profesional;
        this.indicacion = indicacion;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public String getProfesional() {
        return profesional;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public FichaClinica getFicha() {
        return ficha;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

    public void setFicha(FichaClinica ficha) {
        this.ficha = ficha;
    }
}