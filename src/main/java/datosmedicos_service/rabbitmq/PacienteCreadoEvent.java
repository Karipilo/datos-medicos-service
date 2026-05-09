package datosmedicos_service.rabbitmq;

public class PacienteCreadoEvent {

    private Long id;

    private String nombre;

    private String rut;

    public PacienteCreadoEvent() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}