package datosmedicos_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "controles_medicos")
public class ControlMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;

    private String tipoControl;

    private String profesional;

    @Column(length = 2000)
    private String observacion;

    public ControlMedico() {
    }

    public ControlMedico(
            String fecha,
            String tipoControl,
            String profesional,
            String observacion
    ) {
        this.fecha = fecha;
        this.tipoControl = tipoControl;
        this.profesional = profesional;
        this.observacion = observacion;
    }

    public Long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoControl() {
        return tipoControl;
    }

    public String getProfesional() {
        return profesional;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTipoControl(String tipoControl) {
        this.tipoControl = tipoControl;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}