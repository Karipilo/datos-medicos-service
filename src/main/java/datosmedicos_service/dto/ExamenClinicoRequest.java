package datosmedicos_service.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExamenClinicoRequest {

    private Long id;
    private String nombre;
    private String fecha;
    private String estado;
    private String profesional;
    private String observacion;
    private String resultado;
    private Long ficha;
}