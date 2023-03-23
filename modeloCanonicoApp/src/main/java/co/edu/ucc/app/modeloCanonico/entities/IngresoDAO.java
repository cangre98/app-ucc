package co.edu.ucc.app.modeloCanonico.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ingreso")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class IngresoDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idingreso", nullable = false)
    private BigDecimal id;

    @Size(max = 50)
    @NotNull
    @Column(name = "detalle", nullable = false, length = 50)
    private String detalle;

    @NotNull
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcuenta", nullable = false)
    private CuentaDAO idCuenta;



}