package co.edu.ucc.app.modeloCanonico.dto.generic;

import co.edu.ucc.app.modeloTransversal.utils.MensajeValidacion;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRequestEliminacionDTO{

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private BigDecimal id;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    @Size(min = 1, max = 30, message = MensajeValidacion.MSN_ERROR_LONGITUD_USUARIO_REGISTRO)
    private String idUsuarioRegistro;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    @Size(min = 1, max = 30, message = MensajeValidacion.MSN_ERROR_LONGITUD_SESION_REGISTRO)
    private String idSesionRegistro;
}
