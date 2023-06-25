package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.IngresoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IIngresoRepository extends JpaRepository<IngresoDAO, BigDecimal> {

    @Query("SELECT sum(ig.valor) FROM IngresoDAO ig WHERE ig.idCuenta.id = :id")
    BigDecimal sumaIngresosIdCuenta(@Param("id") BigDecimal id);

    @Query("SELECT ig FROM IngresoDAO ig WHERE ig.idCuenta.id = :id")
    List<IngresoDAO> ingresosIdCuenta(@Param("id") BigDecimal id);
}