package co.edu.ucc.app.modeloCanonico.dto;

import co.edu.ucc.app.modeloTransversal.utils.MensajeValidacion;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;



import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private BigDecimal id;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private String correo;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private String clave;
    private Date fechaCreacion;
    private Date fechaCambioClave;

    private PersonaDTO idPersona;


}
