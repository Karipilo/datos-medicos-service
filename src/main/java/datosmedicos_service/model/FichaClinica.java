package datosmedicos_service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ficha_clinica")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String genero;

    @OneToMany(mappedBy = "ficha")
    @JsonManagedReference
    private List<Medicamento> medicamentos;
}