package co.edu.ucc.app.modeloCanonico.dto;

import co.edu.ucc.app.modeloTransversal.utils.MensajeValidacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoPersonaDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private BigDecimal id;
    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private String codigoPersona;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private String descripcionTipoPersona;



}
