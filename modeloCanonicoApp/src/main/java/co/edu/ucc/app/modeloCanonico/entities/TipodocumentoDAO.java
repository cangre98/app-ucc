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
@Table(name = "tipodocumento")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipodocumentoDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipodocumento", nullable = false)
    private BigDecimal id;

    @Size(max = 3)
    @NotNull
    @Column(name = "codigo", nullable = false, length = 3)
    private String codigo;

    @Size(max = 40)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 40)
    private String descripcion;


}