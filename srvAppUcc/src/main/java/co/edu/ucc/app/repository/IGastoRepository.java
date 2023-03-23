package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.GastoDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface IGastoRepository extends JpaRepository<GastoDAO, BigDecimal> {
}