package co.edu.ucc.app.modeloCanonico.dto;

import co.edu.ucc.app.modeloCanonico.entities.InversionDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AhorroDTO  {


    private  BigDecimal id;

    @NotNull
    private  String detalle;

    private  BigDecimal valorAhorro;

    private InversionDAO inversion;

    private CuentaDTO cuenta;
}
