package datosmedicos_service.model;

import jakarta.persistence.*;


@Entity
@Table(name = "ficha_clinica")
public class FichaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombrePaciente;
    private String rutPaciente;
    private Integer edad;
    private String diagnostico;
    private String alergias;
    private String observaciones;

    public FichaClinica() {
    }

    public FichaClinica(String nombrePaciente, String rutPaciente, int edad, String diagnostico, String alergias,
            String observaciones) {
        this.nombrePaciente = nombrePaciente;
        this.rutPaciente = rutPaciente;
        this.edad = edad;
        this.diagnostico = diagnostico;
        this.alergias = alergias;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getRutPaciente() {
        return rutPaciente;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

       

}