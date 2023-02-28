package co.edu.ucc.app.modeloCanonico.entities;

import co.edu.ucc.app.modeloCanonico.entities.TipoPersonaDAO;
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
@Table(name = "persona")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonaDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona", nullable = false)
    private BigDecimal id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idtipodocumento", nullable = false)
    private TipodocumentoDAO idTipoDocumento;

    @Size(max = 15)
    @NotNull
    @Column(name = "numerodocumento", nullable = false, length = 15)
    private String numeroDocumento;

    @Size(max = 40)
    @NotNull
    @Column(name = "primernombre", nullable = false, length = 40)
    private String primerNombre;

    @Size(max = 40)
    @NotNull
    @Column(name = "primerapellido", nullable = false, length = 40)
    private String primerApellido;

    @Size(max = 30)
    @Column(name = "segundonombre", length = 30)
    private String segundoNombre;

    @Size(max = 30)
    @Column(name = "segundoapellido", length = 30)
    private String segundoApellido;

    @Size(max = 15)
    @NotNull
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idtipopersona", nullable = false)
    private TipoPersonaDAO idTipoPersona;


}