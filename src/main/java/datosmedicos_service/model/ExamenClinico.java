package datosmedicos_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "examenes_clinicos")
public class ExamenClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String fecha;

    private String estado;

    private String profesional;

    @Column(length = 2000)
    private String observacion;

    @Column(length = 3000)
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    private FichaClinica ficha;

    public ExamenClinico() {
    }

    public ExamenClinico(
            String nombre,
            String fecha,
            String estado,
            String profesional,
            String observacion,
            String resultado) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.estado = estado;
        this.profesional = profesional;
        this.observacion = observacion;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public String getProfesional() {
        return profesional;
    }

    public String getObservacion() {
        return observacion;
    }

    public FichaClinica getFicha() {
        return ficha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setFicha(FichaClinica ficha) {
        this.ficha = ficha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}