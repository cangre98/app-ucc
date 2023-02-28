package co.edu.ucc.app.modeloCanonico.dto.generic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PosicionCeldaExcelDTO{

    private String fila;
    private String columna;
    private String hoja;


}
