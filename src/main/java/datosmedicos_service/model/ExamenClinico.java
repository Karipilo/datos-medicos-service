package datosmedicos_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String fecha;

    private String estado;

    private String profesional;

    @Column(length = 2000)
    private String observacion;

    @Column(length = 3000)
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    @JsonBackReference
    private FichaClinica ficha;
}