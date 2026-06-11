package datosmedicos_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "evolucion_clinica")
public class EvolucionClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;

    private String profesional;

    @Column(length = 2000)
    private String descripcion;

    @Column(length = 2000)
    private String observaciones;

    private Long pacienteId;

    public EvolucionClinica() {
    }

    public EvolucionClinica(
            String fecha,
            String profesional,
            String descripcion) {
        this.fecha = fecha;
        this.profesional = profesional;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
public void setProfesional(String profesional) {

    System.out.println("SET PROFESIONAL -> " + profesional);

    this.profesional = profesional;
}

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}