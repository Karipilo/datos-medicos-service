package datosmedicos_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String dosis;

    private String frecuencia;

    private String observaciones;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "dias_tratamiento")
    private Integer diasTratamiento;

    private String profesional;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    @JsonBackReference
    private FichaClinica ficha;
}