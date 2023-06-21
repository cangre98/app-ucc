package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.IngresoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IIngresoRepository extends JpaRepository<IngresoDAO, BigDecimal> {

    @Query("SELECT sum(ig.valor) FROM IngresoDAO ig WHERE ig.idCuenta.id = :id")
    BigDecimal sumaIngresosIdCuenta(@Param("id") BigDecimal id);


   @Query("select ig from IngresoDAO ig where YEAR(ig.fechaIngreso)=:anio")
    List<IngresoDAO> consultarFechaPorAnnio(@Param("anio") Integer anio);

    @Query("select ig from IngresoDAO ig where YEAR(ig.fechaIngreso)=:anio " +
            "and MONTH(ig.fechaIngreso)=:mes ")
    List<IngresoDAO> consultarFechaPorAnnioYMes(@Param("anio") Integer anio, @Param("mes") Integer mes);
}