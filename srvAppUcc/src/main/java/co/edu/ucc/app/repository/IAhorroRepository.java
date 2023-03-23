package co.edu.ucc.app.repository;

import co.edu.ucc.app.modeloCanonico.entities.AhorroDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface IAhorroRepository extends JpaRepository<AhorroDAO, BigDecimal> {
}