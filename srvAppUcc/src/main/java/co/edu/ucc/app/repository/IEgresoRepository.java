package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.EgresoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


public interface IEgresoRepository extends JpaRepository<EgresoDAO, BigDecimal> {

    @Query(value = "select eg.valor " +
            "from postgres.public.egresos eg" +
            " where idegreso=:id"
            , nativeQuery = true)
    BigDecimal valorActual(@Param("id") BigDecimal id);

}