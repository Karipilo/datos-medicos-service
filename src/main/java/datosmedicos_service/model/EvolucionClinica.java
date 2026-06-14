package datosmedicos_service.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "evolucion_clinica")
public class EvolucionClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    private String profesional;

    @Column(length = 2000)
    private String descripcion;

    @Column(length = 2000)
    private String observaciones;

    private Long pacienteId;

    public EvolucionClinica() {
    }

    public EvolucionClinica(
            LocalDateTime fechaRegistro,
            String profesional,
            String descripcion) {

        this.fechaRegistro = fechaRegistro;
        this.profesional = profesional;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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