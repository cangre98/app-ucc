package co.edu.ucc.app.modeloCanonico.dto;

import co.edu.ucc.app.modeloCanonico.entities.TipoPersonaDAO;
import co.edu.ucc.app.modeloCanonico.entities.TipodocumentoDAO;
import co.edu.ucc.app.modeloTransversal.utils.MensajeValidacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonaDTO {

    private BigDecimal id;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private TipodocumentoDTO idTipoDocumento;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private String numeroDocumento;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private String primerNombre;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private String primerApellido;

    private String segundoNombre;

    private String segundoApellido;

    private String telefono;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private TipoPersonaDTO idTipoPersona;


}