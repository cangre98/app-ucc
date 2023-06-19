package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.EgresoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;



public interface IEgresoRepository extends JpaRepository<EgresoDAO, BigDecimal> {

    @Query(value = "select eg.valor " +
            "from postgres.public.egreso eg" +
            " where idegreso=:id"
            , nativeQuery = true)
    BigDecimal valorActual(@Param("id") BigDecimal id);

    @Query("SELECT sum(eg.valor) FROM EgresoDAO eg WHERE eg.idCuenta.id = :id")
    BigDecimal sumaEgresosIdCuenta(@Param("id") BigDecimal id);

    @Query("SELECT eg FROM EgresoDAO eg WHERE eg.idCuenta.id = :idCuenta AND eg.idgasto.id = :idGasto")
    List<EgresoDAO> consultarEgresosPorIdCuentaIdEgreso(@Param("idCuenta") BigDecimal idCuenta, @Param("idGasto") BigDecimal idGasto);

    @Query("SELECT eg FROM EgresoDAO eg WHERE eg.idCuenta.id = :id")
    List<EgresoDAO> EgresosIdCuenta(@Param("id") BigDecimal id);

}