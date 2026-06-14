package datosmedicos_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "signos_vitales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignosVitales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String presion;

    private Integer frecuencia;

    private Double temperatura;

    private Integer saturacion;

    private String profesional;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    private FichaClinica ficha;

}