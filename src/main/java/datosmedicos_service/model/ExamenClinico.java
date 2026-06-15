package datosmedicos_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "examenes_clinicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamenClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    private String estado;

    private String profesional;

    @Column(length = 2000)
    private String observacion;

    @Column(length = 3000)
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    @JsonBackReference("ficha-examenes")
    private FichaClinica ficha;
}