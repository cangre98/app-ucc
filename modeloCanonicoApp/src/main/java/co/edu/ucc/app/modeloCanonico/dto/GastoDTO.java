package co.edu.ucc.app.modeloCanonico.dto;

import co.edu.ucc.app.modeloTransversal.utils.MensajeValidacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GastoDTO {

    private BigDecimal id;

    @Size(max = 40)
    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private String descripcion;

}


