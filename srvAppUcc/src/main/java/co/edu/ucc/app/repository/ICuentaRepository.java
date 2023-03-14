package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.CuentaDAO;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface ICuentaRepository extends JpaRepository<CuentaDAO, BigDecimal> {

    @Query(value = "select c.idcuenta from cuenta c where c.idpersona=:idPersona"
    , nativeQuery = true)
    BigDecimal consultarCuentaPorIdPersona (@Param("idPersona") BigDecimal idPersona);

    @Transactional
    @Modifying
    @Query("UPDATE CuentaDAO c set c.descripcion=:descripcion, " +
            "c.saldo=:saldo, c.detalle=:detalle " +
            " WHERE c.id=:id")
    void actualizarCuenta(@Param("descripcion") String descripcion, @Param("saldo") BigDecimal saldo,
                          @Param("detalle") String detalle, @Param("id") BigDecimal idCuenta);


}