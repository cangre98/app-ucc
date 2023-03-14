package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.IngresoDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface IIngresoRepository extends JpaRepository<IngresoDAO, BigDecimal> {
}