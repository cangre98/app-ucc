package co.edu.ucc.app.modeloCanonico.entities;

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
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsuarioDAO implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private BigDecimal id;

    @Column(name = "correo", nullable = false, unique = true, length = 40)
    private String correo;

    @Column(name = "clave", nullable = false, unique = true, length = 40)
    private String clave;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechacreacion", nullable = false, length = 7)
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechacambioclave", nullable = false, length = 7)
    private Date fechaCambioClave;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idpersona", nullable = false)
    private PersonaDAO idPersona;


}
