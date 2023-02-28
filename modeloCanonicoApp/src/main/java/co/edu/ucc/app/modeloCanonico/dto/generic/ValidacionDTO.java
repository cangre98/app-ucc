package co.edu.ucc.app.modeloCanonico.dto.generic;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidacionDTO {

    private String tipoValidacion;
    private String ubicacion;
    private String descripcion;
    private Object contenido;
}
