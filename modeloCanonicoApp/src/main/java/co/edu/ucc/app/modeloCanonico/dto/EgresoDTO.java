package co.edu.ucc.app.modeloCanonico.dto;

import co.edu.ucc.app.modeloTransversal.utils.MensajeValidacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EgresoDTO {


    private  BigDecimal id;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private  String detalle;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private  BigDecimal valor;

    @NotNull(message = MensajeValidacion.MSN_VARIABLE_REQUERIDA)
    private Date fecha;

    private CuentaDTO cuenta;

    private GastoDTO gasto;




}
