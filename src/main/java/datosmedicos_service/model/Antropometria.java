package datosmedicos_service.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "antropometria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Antropometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double peso;

    private Double altura;

    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    @JsonBackReference
    private FichaClinica ficha;
}