package datosmedicos_service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ControlMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String profesional;
    private String observaciones;
    private String estadoPaciente;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    @JsonIgnore
    private FichaClinica ficha;

    public ControlMedico() {}

    public ControlMedico(LocalDate fecha, String profesional, String observaciones, String estadoPaciente, FichaClinica ficha) {
        this.fecha = fecha;
        this.profesional = profesional;
        this.observaciones = observaciones;
        this.estadoPaciente = estadoPaciente;
        this.ficha = ficha;
    }

    public Long getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public String getProfesional() { return profesional; }
    public String getObservaciones() { return observaciones; }
    public String getEstadoPaciente() { return estadoPaciente; }
    public FichaClinica getFicha() { return ficha; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setProfesional(String profesional) { this.profesional = profesional; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public void setEstadoPaciente(String estadoPaciente) { this.estadoPaciente = estadoPaciente; }
    public void setFicha(FichaClinica ficha) { this.ficha = ficha; }
}